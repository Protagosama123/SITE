package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DescriptionTextStyle
import com.example.ui.theme.HeadingTextStyle
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.NavLabelTextStyle
import com.example.ui.theme.SiteBackground
import com.example.ui.theme.SiteBorderLight
import com.example.ui.theme.SiteCardDark
import com.example.ui.theme.SiteCardLight
import com.example.ui.theme.SiteFloatingNav
import com.example.ui.theme.SiteNavDisabled
import com.example.ui.theme.SiteServiceCircle
import com.example.ui.theme.SiteTextPrimary

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        SiteAppScreen()
      }
    }
  }
}

@Composable
fun SiteAppScreen() {
  val context = LocalContext.current
  val scrollState = rememberScrollState()
  var activePressCount by remember { mutableIntStateOf(0) }

  Box(
      modifier = Modifier
          .fillMaxSize()
          .background(SiteBackground)
          .testTag("root_container"),
      contentAlignment = Alignment.TopCenter
  ) {
    // Main scrollable canvas constrained to 390dp width, with scroll modifier before padding
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 390.dp)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp)
            .testTag("scrollable_column"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // 60px spacing from top considering notch as specified
      Spacer(modifier = Modifier.height(60.dp))

      // Header row: SITE with Bell & Avatar
      TopHeaderSection(
          onNotificationClick = {
            Toast.makeText(context, "No new notifications", Toast.LENGTH_SHORT).show()
          }
      )

      Spacer(modifier = Modifier.height(30.dp))

      // "Stay ready your [bookmark] exam awaits" with inline bookmark icon
      ExamAwaitsSection()

      Spacer(modifier = Modifier.height(18.dp))

      // Light gray widget card with two white boxes and a wide bar
      LightWidgetCard()

      Spacer(modifier = Modifier.height(30.dp))

      // "Today Exam" section
      TodayExamSection()

      Spacer(modifier = Modifier.height(30.dp))

      // "Services" section with 4 circles and labels
      ServicesSection()

      Spacer(modifier = Modifier.height(30.dp))

      // "Broadcasts" section
      BroadcastsSection()

      Spacer(modifier = Modifier.height(30.dp))

      // "Circulars" section
      CircularsSection()

      // Ensure content scrolls comfortably well above floating bar
      Spacer(modifier = Modifier.height(140.dp))
    }

    // Floating Navigation Bar positioned 8px from bottom with active 1st item
    FloatingBottomBar(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .widthIn(max = 390.dp)
            .padding(horizontal = 16.dp)
            .navigationBarsPadding()
            .padding(bottom = 8.dp),
        onActiveItemClick = {
          activePressCount++
          Toast.makeText(context, "Home active (Tapped #$activePressCount)", Toast.LENGTH_SHORT).show()
        }
    )
  }
}

@Composable
fun TopHeaderSection(onNotificationClick: () -> Unit) {
  Row(
      modifier = Modifier
          .fillMaxWidth()
          .testTag("top_header_row"),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
        text = "SITE",
        style = HeadingTextStyle,
        color = SiteTextPrimary,
        modifier = Modifier.testTag("title_site")
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
      // Notification bell icon in a circular frame
      Box(
          modifier = Modifier
              .size(48.dp)
              .clip(CircleShape)
              .background(Color.White)
              .border(1.dp, SiteBorderLight, CircleShape)
              .clickable(
                  interactionSource = remember { MutableInteractionSource() },
                  indication = ripple(bounded = true, radius = 24.dp),
                  onClick = onNotificationClick
              )
              .testTag("button_notification"),
          contentAlignment = Alignment.Center
      ) {
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notifications",
            tint = SiteTextPrimary,
            modifier = Modifier.size(24.dp)
        )
      }

      // Empty circular avatar placeholder
      Box(
          modifier = Modifier
              .size(48.dp)
              .clip(CircleShape)
              .background(Color.White)
              .border(1.dp, SiteBorderLight, CircleShape)
              .testTag("avatar_placeholder")
      )
    }
  }
}

@Composable
fun ExamAwaitsSection() {
  val inlineBookmark = mapOf(
      "bookmark" to InlineTextContent(
          Placeholder(
              width = 24.sp,
              height = 30.sp,
              placeholderVerticalAlign = PlaceholderVerticalAlign.Center
          )
      ) {
        Icon(
            imageVector = Icons.Filled.Bookmark,
            contentDescription = "Bookmark",
            tint = SiteTextPrimary,
            modifier = Modifier.size(22.dp)
        )
      }
  )

  val annotatedHeading = buildAnnotatedString {
    append("Stay ready your ")
    appendInlineContent("bookmark")
    append(" exam\nawaits")
  }

  Text(
      text = annotatedHeading,
      inlineContent = inlineBookmark,
      style = HeadingTextStyle,
      color = SiteTextPrimary,
      modifier = Modifier
          .fillMaxWidth()
          .testTag("heading_exam_awaits")
  )
}

@Composable
fun LightWidgetCard() {
  Box(
      modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(18.dp))
          .background(SiteCardLight)
          .padding(horizontal = 16.dp, vertical = 20.dp)
          .testTag("card_light_widget")
  ) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Two white rounded boxes side by side
      Row(
          horizontalArrangement = Arrangement.spacedBy(14.dp),
          verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
            modifier = Modifier
                .width(62.dp)
                .height(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .testTag("widget_box_1")
        )
        Box(
            modifier = Modifier
                .width(62.dp)
                .height(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .testTag("widget_box_2")
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Wide white rounded bar below
      Box(
          modifier = Modifier
              .fillMaxWidth()
              .height(46.dp)
              .clip(RoundedCornerShape(14.dp))
              .background(Color.White)
              .testTag("widget_bar")
      )
    }
  }
}

@Composable
fun TodayExamSection() {
  Column(
      modifier = Modifier
          .fillMaxWidth()
          .testTag("section_today_exam")
  ) {
    Text(
        text = "Today Exam",
        style = HeadingTextStyle,
        color = SiteTextPrimary,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("title_today_exam")
    )

    Spacer(modifier = Modifier.height(14.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(SiteCardDark)
            .testTag("card_today_exam")
    )
  }
}

@Composable
fun ServicesSection() {
  Column(
      modifier = Modifier
          .fillMaxWidth()
          .testTag("section_services")
  ) {
    Text(
        text = "Services",
        style = HeadingTextStyle,
        color = SiteTextPrimary,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("title_services")
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("row_services"),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
      listOf("service 1", "service 2", "service 3", "service 4").forEachIndexed { index, title ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(72.dp)
                .testTag("service_item_$index")
        ) {
          Box(
              modifier = Modifier
                  .size(68.dp)
                  .clip(CircleShape)
                  .background(SiteServiceCircle)
                  .testTag("service_circle_$index")
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
              text = title,
              style = DescriptionTextStyle,
              color = SiteTextPrimary,
              textAlign = TextAlign.Center,
              maxLines = 1,
              softWrap = false,
              modifier = Modifier.testTag("service_label_$index")
          )
        }
      }
    }
  }
}

@Composable
fun BroadcastsSection() {
  Column(
      modifier = Modifier
          .fillMaxWidth()
          .testTag("section_broadcasts")
  ) {
    Text(
        text = "Broadcasts",
        style = HeadingTextStyle,
        color = SiteTextPrimary,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("title_broadcasts")
    )

    Spacer(modifier = Modifier.height(14.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(SiteCardDark)
            .testTag("card_broadcasts")
    )
  }
}

@Composable
fun CircularsSection() {
  Column(
      modifier = Modifier
          .fillMaxWidth()
          .testTag("section_circulars")
  ) {
    Text(
        text = "Circulars",
        style = HeadingTextStyle,
        color = SiteTextPrimary,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("title_circulars")
    )

    Spacer(modifier = Modifier.height(14.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(SiteCardDark)
            .testTag("card_circulars")
    )
  }
}

@Composable
fun FloatingBottomBar(
    modifier: Modifier = Modifier,
    onActiveItemClick: () -> Unit
) {
  Box(
      modifier = modifier
          .shadow(
              elevation = 6.dp,
              shape = RoundedCornerShape(26.dp),
              ambientColor = Color(0x1F000000),
              spotColor = Color(0x1F000000)
          )
          .height(72.dp)
          .clip(RoundedCornerShape(26.dp))
          .background(SiteFloatingNav)
          .testTag("floating_navigation_bar")
  ) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
      // 1. First icon: ACTIVE and INTERACTIVE
      Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center,
          modifier = Modifier
              .size(56.dp)
              .clip(RoundedCornerShape(12.dp))
              .clickable(
                  interactionSource = remember { MutableInteractionSource() },
                  indication = ripple(bounded = true),
                  onClick = onActiveItemClick
              )
              .testTag("nav_item_home_active")
      ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Home Menu",
            tint = SiteTextPrimary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "Home",
            style = NavLabelTextStyle,
            color = SiteTextPrimary,
            modifier = Modifier.testTag("nav_label_0")
        )
      }

      // 2. Second icon: DISABLED and NOT INTERACTIVE
      Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center,
          modifier = Modifier
              .size(56.dp)
              .testTag("nav_item_window_disabled")
      ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_nav_window),
            contentDescription = "Window Dashboard (disabled)",
            tint = SiteNavDisabled,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "Home",
            style = NavLabelTextStyle,
            color = SiteNavDisabled,
            modifier = Modifier.testTag("nav_label_1")
        )
      }

      // 3. Third icon: DISABLED and NOT INTERACTIVE
      Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center,
          modifier = Modifier
              .size(56.dp)
              .testTag("nav_item_folder_disabled")
      ) {
        Icon(
            imageVector = Icons.Default.Folder,
            contentDescription = "Folder (disabled)",
            tint = SiteNavDisabled,
            modifier = Modifier.size(23.dp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "Home",
            style = NavLabelTextStyle,
            color = SiteNavDisabled,
            modifier = Modifier.testTag("nav_label_2")
        )
      }

      // 4. Fourth icon: DISABLED and NOT INTERACTIVE
      Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center,
          modifier = Modifier
              .size(56.dp)
              .testTag("nav_item_profile_disabled")
      ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile (disabled)",
            tint = SiteNavDisabled,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "Home",
            style = NavLabelTextStyle,
            color = SiteNavDisabled,
            modifier = Modifier.testTag("nav_label_3")
        )
      }
    }
  }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun SiteAppPreview() {
  MyApplicationTheme {
    SiteAppScreen()
  }
}
