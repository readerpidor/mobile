package com.matttax.reado.feature.reading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ArticleBody() {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(32.dp),
  ) {
    BodyParagraph(
      text = buildAnnotatedString {
        withStyle(
          SpanStyle(
            fontFamily = FontFamily.Serif,
            fontSize = 48.sp,
          )
        ) { append("C") }
        append("oncrete, often dismissed as cold and unforgiving, holds a profound quietude when shaped with intention. In the sweeping curves and sharp angles of mid-century brutalism, we find not just utility, but a stark, unapologetic honesty.")
      },
    )
    BodyParagraph(
      text = AnnotatedString("The movement emerged from the ashes of conflict, a functionalist response to the need for rapid rebuilding. Yet, architects like Le Corbusier and Paul Rudolph elevated raw concrete—béton brut—into a sculptural medium. They understood that the material's power lay in its mass, its weight, and its interaction with light."),
    )
    AiHighlightBlock()
    BodyParagraph(
      text = AnnotatedString("Critics often point to the weathering of concrete as a flaw, highlighting the streaks and stains that develop over decades. However, this patina is integral to the brutalist aesthetic. It marks the passage of time, turning static monuments into living entities that respond to their environment."),
    )
    Text(
      text = "Light as a Structural Element",
      color = BodyPrimary,
      style = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
      ),
    )
    BodyParagraph(
      text = AnnotatedString("In brutalist architecture, windows are rarely simple apertures; they are deeply recessed voids or carefully angled slices designed to sculpt incoming light. The interplay of stark shadows against textured concrete surfaces creates a dynamic visual drama that shifts throughout the day."),
    )
    BodyParagraph(
      text = AnnotatedString("This dramatic use of chiaroscuro transforms vast interior spaces into sanctuaries of contemplation. The heaviness of the material is paradoxically lifted by the precision of the light it captures, proving that even the densest forms can hold moments of profound grace."),
    )
  }
}
