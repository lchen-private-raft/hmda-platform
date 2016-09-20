package hmda.persistence

import akka.actor.{ ActorRef, ActorSystem }
import akka.pattern.ask
import akka.util.Timeout
import hmda.api.processing.LocalHmdaEventProcessor
import hmda.model.fi.SubmissionId
import hmda.persistence.HmdaSupervisor.{ FindActorByName, FindFilings, FindProcessingActor, FindSubmissions }
import hmda.persistence.institutions.{ FilingPersistence, SubmissionPersistence }
import hmda.persistence.processing.{ HmdaFileParser, HmdaFileValidator, HmdaRawFile, SingleLarValidation }
import org.scalatest.{ MustMatchers, WordSpec }

import scala.concurrent.Await
import scala.concurrent.duration._

class HmdaSupervisorSpec extends WordSpec with MustMatchers {

  val system = ActorSystem()

  val supervisor = system.actorOf(HmdaSupervisor.props(), "supervisor")

  implicit val ec = system.dispatcher
  val timeout = 2.seconds
  implicit val akkaTimeout = Timeout(timeout)

  "The HMDA Supervisor" must {
    "find or create actor by name" in {
      val path = "akka://default/user/supervisor/larValidation"
      val validatorF = (supervisor ? FindActorByName(SingleLarValidation.name)).mapTo[ActorRef]
      val validator = Await.result(validatorF, timeout)
      validator.path.toString mustBe path

      val validator2F = (supervisor ? FindActorByName(SingleLarValidation.name)).mapTo[ActorRef]
      val validator2 = Await.result(validator2F, timeout)
      validator2.path.toString mustBe path

      val eventProcessorPath = "akka://default/user/supervisor/eventProcessor"
      val processorF = (supervisor ? FindActorByName(LocalHmdaEventProcessor.name)).mapTo[ActorRef]
      val processor = Await.result(processorF, timeout)
      processor.path.toString mustBe eventProcessorPath

      val processor2F = (supervisor ? FindActorByName(LocalHmdaEventProcessor.name)).mapTo[ActorRef]
      val processor2 = Await.result(processor2F, timeout)
      processor2.path.toString mustBe eventProcessorPath
    }

    "find or create filings actor" in {
      val institutionId = "0"
      val path = s"akka://default/user/supervisor/filings-$institutionId"
      val filingsF = (supervisor ? FindFilings(FilingPersistence.name, institutionId)).mapTo[ActorRef]
      val filings = Await.result(filingsF, timeout)
      filings.path.toString mustBe path

      val filings2F = (supervisor ? FindFilings(FilingPersistence.name, institutionId)).mapTo[ActorRef]
      val filings2 = Await.result(filings2F, timeout)
      filings2.path.toString mustBe path
    }

    "find or create submissions actor" in {
      val institutionId = "0"
      val period = "2017"
      val submissionsPath = s"akka://default/user/supervisor/submissions-$institutionId-$period"
      val submissionsF = (supervisor ? FindSubmissions(SubmissionPersistence.name, institutionId, period)).mapTo[ActorRef]
      val submissions = Await.result(submissionsF, timeout)
      submissions.path.toString mustBe submissionsPath

      val submissions2F = (supervisor ? FindSubmissions(SubmissionPersistence.name, institutionId, period)).mapTo[ActorRef]
      val submissions2 = Await.result(submissions2F, timeout)
      submissions2.path.toString mustBe submissionsPath
    }

    "find or create processing actors" in {
      val submissionId = SubmissionId("0", "2017", 0)
      val rawPath = s"akka://default/user/supervisor/HmdaRawFile-0-2017-0"
      val rawFileF = (supervisor ? FindProcessingActor(HmdaRawFile.name, submissionId)).mapTo[ActorRef]
      val rawFile = Await.result(rawFileF, timeout)
      rawFile.path.toString mustBe rawPath

      val rawFile2F = (supervisor ? FindProcessingActor(HmdaRawFile.name, submissionId)).mapTo[ActorRef]
      val rawFile2 = Await.result(rawFile2F, timeout)
      rawFile2.path.toString mustBe rawPath

      val parsePath = s"akka://default/user/supervisor/HmdaFileParser-0-2017-0"
      val parseFileF = (supervisor ? FindProcessingActor(HmdaFileParser.name, submissionId)).mapTo[ActorRef]
      val parseFile = Await.result(parseFileF, timeout)
      parseFile.path.toString mustBe parsePath

      val parseFile2F = (supervisor ? FindProcessingActor(HmdaFileParser.name, submissionId)).mapTo[ActorRef]
      val parseFile2 = Await.result(parseFile2F, timeout)
      parseFile2.path.toString mustBe parsePath

      val validatePath = s"akka://default/user/supervisor/HmdaFileValidator-0-2017-0"
      val validateFileF = (supervisor ? FindProcessingActor(HmdaFileValidator.name, submissionId)).mapTo[ActorRef]
      val validateFile = Await.result(validateFileF, timeout)
      validateFile.path.toString mustBe validatePath

      val validateFile2F = (supervisor ? FindProcessingActor(HmdaFileValidator.name, submissionId)).mapTo[ActorRef]
      val validateFile2 = Await.result(validateFile2F, timeout)
      validateFile2.path.toString mustBe validatePath

    }

    "terminate ActorSystem" in {
      Thread.sleep(2000)
      system.terminate()
      system.whenTerminated.map { isTerminated =>
        isTerminated mustBe true
      }
    }

  }

}
