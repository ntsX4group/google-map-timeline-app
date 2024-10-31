package kokorowa.timeline

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import google_map_timeline_app.composeapp.generated.resources.Res
import google_map_timeline_app.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        var attendancing by remember { mutableStateOf(false) }
        var breaking by remember { mutableStateOf(false) }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(Modifier.fillMaxWidth().drawBehind {
                    val strokeWidth = 1.dp.toPx() * density
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        Color.LightGray,
                        Offset(0f, y + 10 ),
                        Offset(size.width, y),
                        strokeWidth
                    )
                }, contentAlignment = Alignment.TopStart) {
                    Box(Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 5.dp, start = 10.dp)) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(50.dp),
                                painter = painterResource(Res.drawable.logo),
                                contentDescription = "Some icon",
                                tint = Color.Unspecified
                            )
                            Text("c", color = Color.Red, fontSize = 30.sp)
                            Text("ocorowa", color = Color.Gray, fontSize = 30.sp)
                            Text("勤怠", color = Color.Gray, fontSize = 30.sp)
                        }
                    }
                }
            }
            AnimatedVisibility(!attendancing) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(Modifier.fillMaxWidth().padding(top = 50.dp, bottom = 20.dp), contentAlignment = Alignment.Center) {
                        Button(onClick = { attendancing = !attendancing },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(255, 138, 141)),
                            modifier = Modifier.height(100.dp).width(250.dp),
                            shape = RoundedCornerShape(10)) {
                            Text("出勤", color = Color.White, fontSize = 30.sp)
                        }
                    }
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("訪問の場合は１件目の訪問先")
                        Text("事務所や業務開始場所に到着時")
                        Text("出勤ボタンを押してください")
                    }
                }
            }
            AnimatedVisibility(attendancing) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    AnimatedVisibility(!breaking) {
                        Box(Modifier.fillMaxWidth().padding(top = 50.dp, bottom = 20.dp), contentAlignment = Alignment.Center) {
                            Button(onClick = { breaking = !breaking },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(41, 201, 150)),
                                modifier = Modifier.height(100.dp).width(250.dp),
                                shape = RoundedCornerShape(10)) {
                                Text("私用・休憩", color = Color.White, fontSize = 30.sp)
                            }
                        }
                    }
                    AnimatedVisibility(breaking) {
                        Box(Modifier.fillMaxWidth().padding(top = 50.dp, bottom = 20.dp), contentAlignment = Alignment.Center) {
                            Button(onClick = { breaking = !breaking },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(41,201,150)),
                                modifier = Modifier.height(100.dp).width(250.dp),
                                shape = RoundedCornerShape(10)) {

                                Column(Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("私用・休憩", color = Color.White, fontSize = 30.sp)
                                    Text("終了", color = Color.White, fontSize = 30.sp)
                                }
                            }
                        }
                    }
                    Box(Modifier.fillMaxWidth().padding(top = 30.dp, bottom = 20.dp), contentAlignment = Alignment.Center) {
                        Button(onClick = { attendancing = !attendancing },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(80, 161, 223)),
                            modifier = Modifier.height(100.dp).width(250.dp),
                            shape = RoundedCornerShape(10),
                            enabled = !breaking) {
                            Text("退勤", color = Color.White, fontSize = 30.sp)
                        }
                    }
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("訪問の場合は最後の訪問先終了時")
                        Text("事務所や業務終了時点で")
                        Text("退勤ボタンを押してください")
                    }
                }
            }
        }
    }
}
