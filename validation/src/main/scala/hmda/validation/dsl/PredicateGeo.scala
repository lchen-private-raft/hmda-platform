package hmda.validation.dsl

import hmda.model.census.{ CBSAMetroMicroLookup, CBSATractLookup }
import hmda.model.fi.lar.Geography

object PredicateGeo {
  private val cbsaTracts = CBSATractLookup.values

  private val cbsaMetroMicro = CBSAMetroMicroLookup.values

  private val validMsaCombinationSet = cbsaTracts.map { cbsa =>
    (cbsa.geoIdMsa, cbsa.state, cbsa.county, cbsa.tractDecimal)
  }.toSet

  private val validMdCombinationSet = cbsaTracts.map { cbsa =>
    (cbsa.metDivFp, cbsa.state, cbsa.county, cbsa.tractDecimal)
  }.toSet

  private val validMsaCombinationSetNoTract = cbsaTracts.map { cbsa =>
    (cbsa.geoIdMsa, cbsa.state, cbsa.county)
  }.toSet

  private val validMdCombinationSetNoTract = cbsaTracts.map { cbsa =>
    (cbsa.metDivFp, cbsa.state, cbsa.county)
  }.toSet

  private val MsaNotMicro = cbsaMetroMicro
    .filter(_.MEMI == 1)
    .map { _.GEOIOD }

  private val hasMsaNotMicroSet = cbsaTracts
    .filter(cbsa => MsaNotMicro.contains(cbsa.geoIdMsa))
    .map(cbsa => (cbsa.state, cbsa.county))
    .toSet

  private val validStateCountyTractCombinationSet = cbsaTracts
    .map { cbsa => (cbsa.state, cbsa.county, cbsa.tractDecimal) }
    .toSet

  private val validStateCountyCombinationSet = cbsaTracts.map { cbsa =>
    (cbsa.state, cbsa.county)
  }.toSet

  private val smallCounties = cbsaTracts
    .filter { cbsa => cbsa.smallCounty == 1 }
    .map { cbsa => (cbsa.state, cbsa.county) }
    .toSet

  implicit def smallCounty: Predicate[Geography] = new Predicate[Geography] {
    override def validate: (Geography) => Boolean = (geo) =>
      smallCounties.contains((geo.state, geo.county))
    override def failure: String = "county is not small"
  }

  implicit def validStateCountyCombination: Predicate[Geography] = new Predicate[Geography] {
    override def validate: (Geography) => Boolean = (geo) =>
      validStateCountyCombinationSet.contains((geo.state, geo.county))
    override def failure: String = "state and county combination is not valid"
  }

  implicit def validStateCountyTractCombination: Predicate[Geography] = new Predicate[Geography] {
    override def validate: (Geography) => Boolean = (geo) =>
      validStateCountyTractCombinationSet.contains((geo.state, geo.county, geo.tract))
    override def failure: String = "state, county, and census tract combination is not valid"
  }

  implicit def validCompleteCombination: Predicate[Geography] = new Predicate[Geography] {
    override def validate: (Geography) => Boolean = (geo) =>
      validMsaCombinationSet.contains((geo.msa, geo.state, geo.county, geo.tract)) ||
        validMdCombinationSet.contains((geo.msa, geo.state, geo.county, geo.tract))
    override def failure: String = "state, county, msa, and census tract combination is not valid"
  }

  implicit def validStateCountyMsaCombination: Predicate[Geography] = new Predicate[Geography] {
    override def validate: (Geography) => Boolean = (geo) =>
      validMsaCombinationSetNoTract.contains((geo.msa, geo.state, geo.county)) ||
        validMdCombinationSetNoTract.contains((geo.msa, geo.state, geo.county))
    override def failure: String = "state, county, msa, and census tract combination is not valid"
  }

  implicit def stateCountyCombinationInMsa: Predicate[Geography] = new Predicate[Geography] {
    override def validate: (Geography) => Boolean = (geo) =>
      hasMsaNotMicroSet.contains((geo.state, geo.county))
    override def failure: String = "state, county, msa, and census tract combination is not valid"
  }

}
