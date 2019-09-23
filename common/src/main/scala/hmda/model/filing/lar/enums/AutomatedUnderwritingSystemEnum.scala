package hmda.model.filing.lar.enums

sealed trait AutomatedUnderwritingSystemEnum extends LarEnum

object AutomatedUnderwritingSystemEnum extends LarCodeEnum[AutomatedUnderwritingSystemEnum] {
  override val values = List(0, 1, 2, 3, 4, 5)

  override def valueOf(code: Int): AutomatedUnderwritingSystemEnum =
    code match {
      case 0    => EmptyAUSValue
      case 1    => DesktopUnderwriter
      case 2    => LoanProspector
      case 3    => TechnologyOpenToApprovedLenders
      case 4    => GuaranteedUnderwritingSystem
      case 5    => OtherAUS
      case 6    => AUSNotApplicable
      case 1111 => AUSExempt
      case _    => InvalidAutomatedUnderwritingSystemCode

    }
}

case object EmptyAUSValue extends AutomatedUnderwritingSystemEnum {
  override def code: Int           = 0
  override def description: String = "Empty Value"
}

case object DesktopUnderwriter extends AutomatedUnderwritingSystemEnum {
  override val code: Int           = 1
  override val description: String = "Desktop Underwriter (DU)"
}

case object LoanProspector extends AutomatedUnderwritingSystemEnum {
  override val code: Int = 2
  override val description: String =
    "Loan Prospector (LP) or Loan Product Advisor"
}

case object TechnologyOpenToApprovedLenders extends AutomatedUnderwritingSystemEnum {
  override val code: Int = 3
  override val description: String =
    "Technology Open to Approved Lenders (TOTAL) Scorecard"
}

case object GuaranteedUnderwritingSystem extends AutomatedUnderwritingSystemEnum {
  override val code: Int           = 4
  override val description: String = "Guaranteed Underwriting System (GUS)"
}

case object OtherAUS extends AutomatedUnderwritingSystemEnum {
  override val code: Int           = 5
  override val description: String = "Other"
}

case object AUSNotApplicable extends AutomatedUnderwritingSystemEnum {
  override val code: Int           = 6
  override val description: String = "Not App"
}

case object AUSExempt extends AutomatedUnderwritingSystemEnum {
  override def code: Int           = 1111
  override def description: String = "Exempt AUS"
}

case object InvalidAutomatedUnderwritingSystemCode extends AutomatedUnderwritingSystemEnum {
  override def code: Int           = -1
  override def description: String = "Invalid Code"
}
