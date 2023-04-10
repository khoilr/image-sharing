package com.example.finalproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.finalproject.R
import com.example.finalproject.screens.ui.theme.FinalProjectTheme

@Composable
fun Profile() {
	Column(
		// center in screen
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
	) {
		Image(
			modifier = Modifier
				.padding(64.dp)
				.size(160.dp)
				.clip(CircleShape),
			painter = rememberAsyncImagePainter("https://scontent-hkg4-2.xx.fbcdn.net/v/t39.30808-6/336768291_1174853683228843_3934748568322515519_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=5cd70e&_nc_ohc=rL4qRgXQMXsAX-i4bpa&_nc_oc=AQmjuYn_DvfRq8oKjZpJtW5PHjPY5qoFhjk34f6iPQrNoZwb4fM-BXIvTGBVplIOshmaPENee4UCds-7LU5Dx8ay&_nc_ht=scontent-hkg4-2.xx&oh=00_AfA3cstqZY8SoBYYwhZ6KN_Egy7_czo-9jXT_AxSiuHk7w&oe=641DF8C9"),
			contentDescription = null,
			contentScale = ContentScale.Crop
		)
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp, vertical = 64.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically,
			content = {
				Row(
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.spacedBy(8.dp),
					content = {
						Icon(
							painterResource(id = R.drawable.mail_fill0_wght400_grad0_opsz48),
							contentDescription = null,
							modifier = Modifier.size(24.dp)
						)
						Text(
							"Email",
							fontWeight = FontWeight.Bold,
							fontSize = 24.sp,
						)
					},
				)
				Text("lcngmnhkhoi@gmail.com")
			},
		)
	}

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
	FinalProjectTheme {
		Profile()
	}
}