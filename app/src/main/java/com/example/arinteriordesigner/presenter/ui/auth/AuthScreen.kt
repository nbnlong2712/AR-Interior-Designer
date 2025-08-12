import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.arinteriordesigner.R
import com.example.arinteriordesigner.core.utils.Route
import com.example.arinteriordesigner.domain.viewmodel.AuthViewModel
import com.example.arinteriordesigner.presenter.ui.auth.AuthContent

@SuppressLint("ContextCastToActivity")
@Composable
@PreviewScreenSizes
fun AuthScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val isLoginSuccess by viewModel.isLoginSuccess.collectAsState()
    val message by viewModel.message.collectAsState()

    LaunchedEffect(isLoginSuccess) {
        if (isLoginSuccess) {
            navController.navigate(Route.HOME) {
                popUpTo(Route.AUTH) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.floralwhite_0))
            .wrapContentWidth(align = Alignment.CenterHorizontally)
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 500.dp)
                .fillMaxSize()
                .background(color = colorResource(R.color.floralwhite_0))
        ) {
            AuthContent(
                onGoogleLogin = { viewModel.signInWithGoogle(context) },
                onFacebookLogin = { viewModel.signInWithFacebook(activity) }
            )
        }
    }
}