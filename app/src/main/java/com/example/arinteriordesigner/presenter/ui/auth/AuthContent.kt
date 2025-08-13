package com.example.arinteriordesigner.presenter.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arinteriordesigner.R
import com.example.arinteriordesigner.presenter.component.BoxImage
import com.example.arinteriordesigner.presenter.component.LoginButton

@Composable
fun AuthContent(
    onGoogleLogin: () -> Unit,
    onFacebookLogin: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.floralwhite_0))
            .wrapContentWidth(Alignment.CenterHorizontally)
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
                    .padding(all = 6.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(start = 8.dp, end = 8.dp, top = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginButton(
                    onClick = onGoogleLogin,
                    backgroundColor = R.color.white,
                    text = "Continue with Google",
                    icon = R.drawable.icon_google,
                )
                LoginButton(
                    onClick = onFacebookLogin,
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
                        .padding(horizontal = 16.dp, vertical = 26.dp),
                    text = "By continuing, you agree to the Terms & Privacy Policy",
                    style = TextStyle(fontSize = 16.sp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}