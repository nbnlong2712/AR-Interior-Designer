import android.annotation.SuppressLint
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.arinteriordesigner.R


@Composable
@PreviewScreenSizes
fun AuthScreen() {
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    BoxImage(
                        imageSrc = R.drawable.auth_1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2.5f)
                            .padding(bottom = 14.dp)
                    )
                    Spacer(modifier = Modifier.weight(3.8f))
                    BoxImage(
                        imageSrc = R.drawable.auth_2,
                        modifier = Modifier
                            .widthIn(max = 150.dp)
                            .fillMaxWidth()
                            .weight(1.5f)
                            .padding(bottom = 14.dp)
                    )
                }

                Row(modifier = Modifier.fillMaxSize()) {
                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        modifier = Modifier
                            .weight(5f)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .wrapContentHeight(Alignment.CenterVertically),
                            text = "Welcome to",
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 32.sp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .wrapContentHeight(Alignment.CenterVertically)
                                .padding(top = 6.dp),
                            text = "AR Interior Designer",
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 30.sp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .wrapContentHeight(Alignment.CenterVertically)
                                .padding(top = 12.dp),
                            text = "The best way to experience interior design",
                            style = TextStyle(fontSize = 16.sp),
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            BoxImage(
                imageSrc = R.drawable.auth_3,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginButton(
                    onClick = {},
                    backgroundColor = R.color.white,
                    text = "Continue with Google",
                    icon = R.drawable.icon_google,
                )
                LoginButton(
                    onClick = {},
                    backgroundColor = R.color.teal_3,
                    text = "Continue with Facebook",
                    icon = R.drawable.icon_facebook,
                    textColor = R.color.white
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    text = "By continuing, you agree to the Terms & Privacy Policy",
                    style = TextStyle(fontSize = 16.sp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

fun getScaleType(maxWidth: Dp, maxHeight: Dp = 1.dp): ContentScale {
    val aspectRatio = maxWidth / maxHeight

    return if (aspectRatio > 1f) {
        ContentScale.FillHeight
    } else {
        ContentScale.FillWidth
    }
}

@Composable
private fun LoginButton(
    onClick: () -> Unit,
    @ColorRes backgroundColor: Int,
    text: String,
    @DrawableRes icon: Int,
    @ColorRes textColor: Int = R.color.black,
) {
    OutlinedButton(
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .widthIn(max = 400.dp)
            .heightIn(max = 60.dp)
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = colorResource(id = backgroundColor),
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier.padding(end = 6.dp)
        )
        Text(text = text, fontSize = 16.sp, color = colorResource(id = textColor))
    }
}

@Composable
fun BoxImage(@DrawableRes imageSrc: Int, modifier: Modifier) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val scaleType = getScaleType(this.maxWidth, this.maxHeight)

        Image(
            painter = painterResource(imageSrc),
            contentDescription = null,
            contentScale = scaleType,
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}