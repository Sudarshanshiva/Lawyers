package com.example.profile.screens

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.profile.R
import com.example.profile.route.Routes
import com.example.profile.viewmodel.ProfileViewModel

@Composable
fun Profile(navController: NavController) {
    var userName by remember { mutableStateOf("") }
    var specialization by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    var experience by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUriBar by remember { mutableStateOf<Uri?>(null) }
    var imageUriAadhar by remember { mutableStateOf<Uri?>(null) }


    val profileViewModel: ProfileViewModel = viewModel()
    val profilefirebaseUser by profileViewModel.profilefirebase.observeAsState(null)


    val options = listOf("Male", "Female", "Other")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(options[0]) }

    val context = LocalContext.current

    val permissionToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        android.Manifest.permission.READ_MEDIA_IMAGES
    } else {
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> imageUri = uri })
    val launcherBar = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> imageUriBar = uri })
    val launcherAadhar = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> imageUriAadhar = uri })

    val permissionlauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {

            } else {

            }
        }
    LaunchedEffect(profilefirebaseUser) {
        if (profilefirebaseUser != null) {
            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
            navController.navigate(Routes.Home.routes) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black, shape = RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(painter = if(imageUri==null)painterResource(id = R.drawable.lawyer)
                else rememberAsyncImagePainter(model = imageUri)
                    , contentDescription = "Profile",
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .clickable {

                            val isGranted = ContextCompat.checkSelfPermission(
                                context, permissionToRequest
                            ) == PackageManager.PERMISSION_GRANTED

                            if (isGranted) {
                                launcher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            } else {
                                permissionlauncher.launch(permissionToRequest)
                            }

                        }, contentScale = ContentScale.Crop)
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(thickness = 2.dp)
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = userName,
                    onValueChange = {
                        userName = it
                    }, textStyle = TextStyle(
                        color = Color.White
                    ), label = {
                        Text(
                            text = "User Name",
                            color = Color.White
                        )
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(thickness = 2.dp)
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = specialization,
                    onValueChange = {
                        specialization = it
                    }, textStyle = TextStyle(
                        color = Color.White
                    ), label = {
                        Text(
                            text = "Specialization",
                            color = Color.White
                        )
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(thickness = 2.dp)
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = city,
                    onValueChange = {
                        city = it
                    },
                    label = {
                        Text(
                            text = "City",
                            color = Color.White
                        )
                    },
                    textStyle = TextStyle(
                        color = Color.White
                    ),

                    )

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(thickness = 2.dp)
                Spacer(modifier = Modifier.height(20.dp))

                Column {
                    Text(text = "Gender")

                    Row(Modifier.selectableGroup()) {
                        options.forEach { text ->
                            Row(
                                Modifier
                                    .padding(5.dp)
                                    .selectable(
                                        selected = (text == selectedOption),
                                        onClick = { onOptionSelected(text) },
                                        role = Role.RadioButton,

                                        )
                                    .padding(8.dp),
                            ) {
                                RadioButton(
                                    selected = (text == selectedOption),
                                    onClick = null
                                )
                                Text(
                                    text = text,
                                    modifier = Modifier.padding(start = 8.dp),
                                    color = Color.White

                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(thickness = 2.dp)
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = experience,
                    onValueChange = {
                        experience = it
                    },
                    label = {
                        Text(
                            text = "Experience",
                            color = Color.White
                        )
                    },
                    textStyle = TextStyle(
                        color = Color.White
                    ),
                )
                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(thickness = 2.dp)
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Upload Bar Concil Document",
                    color = Color.White
                )

                Image(painter = if (imageUriBar == null) painterResource(id = R.drawable.lawyer)
                else rememberAsyncImagePainter(model = imageUriBar),
                    contentDescription = "Bar Photo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(96.dp)
                        .clip(RectangleShape)
                        .background(Color.Gray)
                        .clickable {

                            val isGranted = ContextCompat.checkSelfPermission(
                                context, permissionToRequest
                            ) == PackageManager.PERMISSION_GRANTED

                            if (isGranted) {
                                launcherBar.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            } else {
                                permissionlauncher.launch(permissionToRequest)
                            }

                        },
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(thickness = 2.dp)
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Upload Aadhar Card Document",
                    color = Color.White
                )

                Image(painter = if (imageUriAadhar == null) painterResource(id = R.drawable.lawyer)
                else rememberAsyncImagePainter(model = imageUriAadhar),
                    contentDescription = "Aadhar Card",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(96.dp)
                        .clip(RectangleShape)
                        .background(Color.Gray)
                        .clickable {

                            val isGranted = ContextCompat.checkSelfPermission(
                                context, permissionToRequest
                            ) == PackageManager.PERMISSION_GRANTED

                            if (isGranted) {
                                launcherAadhar.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            } else {
                                permissionlauncher.launch(permissionToRequest)
                            }

                        },
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(start = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    onClick = {
                       profileViewModel.saveImage(imageUri!!,userName,specialization,city,gender,experience,imageUriBar!!,imageUriAadhar!!,profilefirebaseUser!!.uid,context)
                    }
                ) {

                    Text(text = "Save",
                        color = Color.Black)
                }
            }

        }
    }
}


