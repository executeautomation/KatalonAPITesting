import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.HttpBodyContent
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import java.util.List
import java.util.concurrent.locks.Condition
import java.util.ArrayList

//PUT object
def request = (RequestObject)findTestObject('PUT')
String body = '{ "id": 3, "title": "Appiums", "author": "Karthik KK"}'

try{
	request.setBodyContent(new HttpTextBodyContent(body,"UTF-8", "application/json"))
	//request.setRestUrl("http://localhost:3000/posts/3")
	def url = request.getRestUrl()
	url = url.replace('{postid}','3')
	request.setRestUrl(url)
}
catch(Exception ex){
	println (ex.detailMessage)
}
//Make POST request
WS.sendRequest(request)

//Verify response with GET
def response = (RequestObject)findTestObject('GET')

List<TestObjectProperty> params = new ArrayList();
params.add(new TestObjectProperty("id", ConditionType.EQUALS, "3"))
response.setRestParameters(params)

//Make GET Request
def result = WS.sendRequest(response)
//Verify title from response
WS.verifyElementPropertyValue(result, "title[0]", "Appiums", FailureHandling.STOP_ON_FAILURE)



