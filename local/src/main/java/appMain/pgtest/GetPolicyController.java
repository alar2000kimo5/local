package appMain.pgtest;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @program: lis7-ind
 * @description：使用網投API:providePolicyNo和onlineEntryInsuredPolicy在核心落地保單
 * @author:
 * @create: 2021-07-30 17:03
 **/
public class GetPolicyController {

	/** 保單取號API URL */
	private static String providePolicyNoUrl = "http://10.1.113.12:9080/twl/v1.0/providePolicyNo";
	/** 網投投保资料落地核心API URL */
	private static String onlineEntryInsuredPolicyUrl = "http://10.1.113.12:9080/twl/v1.0/onlineEntryInsuredPolicy";
	/** 造單數量 */
	private int PolicyCount = 1;
	/** 保單取號請求電文 */
	private String providePolicyNoQuery = "{\r\n" + "  \"ciType\": \"N\",\r\n" + "  \"planType1\": \"1\",\r\n"
			+ "  \"planType2\": \"\",\r\n" + "  \"processDate\": \"20210730\",\r\n"
			+ "  \"processTime\": \"133605684\",\r\n" + "  \"userId\": \"E248434196\"\r\n" + "}";

	public static void main(String[] args) throws IOException, InterruptedException {

		// 方法1
		int k = 0;
		while (k < 5) {
			Thread.sleep(500);
			new Thread(() -> {
				try {
					GetPolicyController tGetPolicyController = new GetPolicyController();
					tGetPolicyController.dealMain();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}).start();
			k++;
		}
	}

	/** 網投投保资料落地核心請求電文 */

	public void dealMain() {
		try {

			// 1. 得到policyNo :POST /twl/{version}/providePolicyNo
			String ResponseStr = getRes(providePolicyNoUrl, providePolicyNoQuery);

			JSONObject jsonObject02 = printJsonobj(someobj(ResponseStr), "returnData", "policyNo");

			String policyNo = jsonObject02.get("policyNo").toString();

			// 得到結果:保單號: POST /twl/{version}/onlineEntryInsuredPolicy
			System.out.println("開始使用假資料模擬網投保單落地核心......");
			String ResponseOEIPStr = getRes(onlineEntryInsuredPolicyUrl, getSQL(policyNo));

			System.out.println("最終結果是 :" + ResponseOEIPStr);

			JSONObject jsonObject03 = new JSONObject(ResponseOEIPStr);
			System.out.println("returnMessage:" + jsonObject03.get("returnMessage").toString());

			// 擷取""保單號碼為：9000104829,請求成功。"其中的保單號"

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getRes(String url, String query) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(query, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
		return response.getBody();
	}

	public JSONObject someobj(String ResponseStr) {
		JSONObject jsonObject = new JSONObject(ResponseStr);
		jsonObject.get("returnData");
		System.out.println("returnData是:" + jsonObject.get("returnData"));
		return jsonObject;
	}

	public JSONObject printJsonobj(JSONObject jsonObject, String jsonstring, String cloumn) {
		JSONObject jsonObject02 = new JSONObject(jsonObject.get(jsonstring).toString());
		System.out.println(cloumn + ":" + jsonObject02.get(cloumn).toString());
		return jsonObject02;
	}

	public static void mydealmain() {
		System.out.println(Thread.currentThread().getId() + " :" + System.currentTimeMillis());
	}

	public String getSQL(String policyNo) {
		return "{\r\n" + "  \"creditCardList\": [\r\n" + "    {\r\n" + "      \"accountNum\": \"4003610000000010\",\r\n"
				+ "      \"authorCode\": \"123456\",\r\n" + "      \"authorStatus\": \"Y\",\r\n"
				+ "      \"authorizer\": \"汪透意\",\r\n" + "      \"authorizerID\": \"V120927298\",\r\n"
				+ "      \"cardValidate\": \"0422\",\r\n" + "      \"sameCardYn\": \"Y\"\r\n" + "    }\r\n" + "  ],\r\n"
				+ "  \"crs\": {\r\n" + "    \"lcCrs\": {\r\n" + "      \"crsType\": \"N\",\r\n"
				+ "      \"idNo\": \"V120927298\",\r\n" + "      \"idType\": \"1\",\r\n"
				+ "      \"name\": \"汪透意\",\r\n" + "      \"noticeDate\": \"20210722\"\r\n" + "    }\r\n" + "  },\r\n"
				+ "  \"fatcaInfoList\": [\r\n" + "    {\r\n" + "      \"fatcaInfo\": {\r\n"
				+ "        \"bornCountry\": \"1\",\r\n" + "        \"fatcaSelection\": \"1\",\r\n"
				+ "        \"fatcaStatementDate\": \"20210722\"\r\n" + "      }\r\n" + "    }\r\n" + "  ],\r\n"
				+ "  \"informList\": [\r\n" + "    {\r\n" + "      \"idNo\": \"V120927298\",\r\n"
				+ "      \"idType\": \"1\",\r\n" + "      \"informNoticeList\": [\r\n" + "        {\r\n"
				+ "          \"impartCode\": \"B12\",\r\n" + "          \"impartContent\": \"\",\r\n"
				+ "          \"impartOption\": \"N\",\r\n" + "          \"impartVer\": \"D08\"\r\n" + "        }\r\n"
				+ "      ]\r\n" + "    }\r\n" + "  ],\r\n" + "  \"lcAppnt\": {\r\n"
				+ "    \"ageOfInsurance\": \"23\",\r\n" + "    \"appntBirthday\": \"19990101\",\r\n"
				+ "    \"appntName\": \"汪透意\",\r\n" + "    \"appntSex\": \"M\",\r\n"
				+ "    \"avoirdupois\": \"75\",\r\n" + "    \"city\": \"金門縣\",\r\n" + "    \"custodyFlag\": \"N\",\r\n"
				+ "    \"description\": \"金門縣金寧鄉中山路２０號\",\r\n" + "    \"disabilityFlag\": \"N\",\r\n"
				+ "    \"district\": \"金寧鄉\",\r\n" + "    \"email\": \"yen-chun.kao@taiwanlife.com\",\r\n"
				+ "    \"homePhone\": \"\",\r\n" + "    \"idNo\": \"V120927298\",\r\n" + "    \"idType\": \"1\",\r\n"
				+ "    \"interestedPartyYn\": \"N\",\r\n" + "    \"mobile\": \"0999888877\",\r\n"
				+ "    \"nativePlace\": \"TW\",\r\n" + "    \"occupationCode\": \"16010010\",\r\n"
				+ "    \"occupationType\": \"1\",\r\n" + "    \"partCode\": \"\",\r\n" + "    \"partType\": \"\",\r\n"
				+ "    \"relationToInsured\": \"1\",\r\n" + "    \"stature\": \"180\",\r\n"
				+ "    \"zipCode\": \"892\"\r\n" + "  },\r\n" + "  \"lcBnfList\": [\r\n" + "    {\r\n"
				+ "      \"lcBnfSchema\": {\r\n" + "        \"birthday\": \"\",\r\n"
				+ "        \"bnfGrade\": \"1\",\r\n" + "        \"bnfLot\": \"100\",\r\n"
				+ "        \"bnfReason\": \"\",\r\n" + "        \"bnfType\": \"1\",\r\n" + "        \"city\": \"\",\r\n"
				+ "        \"description\": \"\",\r\n" + "        \"distrType\": \"1\",\r\n"
				+ "        \"district\": \"\",\r\n" + "        \"homePhone\": \"\",\r\n" + "        \"idNo\": \"\",\r\n"
				+ "        \"idType\": \"1\",\r\n" + "        \"insuredNo\": \"V120927298\",\r\n"
				+ "        \"interestedPartyYn\": \"\",\r\n" + "        \"mobile\": \"\",\r\n"
				+ "        \"name\": \"\",\r\n" + "        \"nativePlace\": \"\",\r\n"
				+ "        \"relationToInsured\": \"10\",\r\n" + "        \"zipCode\": \"\"\r\n" + "      }\r\n"
				+ "    }\r\n" + "  ],\r\n" + "  \"lcCoMarketList\": [\r\n" + "    {\r\n"
				+ "      \"ifAgree\": \"Y\",\r\n" + "      \"ifEnclose\": \"Y\",\r\n"
				+ "      \"statementDate\": \"20210722\"\r\n" + "    }\r\n" + "  ],\r\n" + "  \"lcCont\": {\r\n"
				+ "    \"acceptDt\": \"20210722\",\r\n" + "    \"acceptNumber\": \"\",\r\n"
				+ "    \"acceptSuccessYn\": \"N\",\r\n" + "    \"appFlag\": \"0\",\r\n"
				+ "    \"autoPayFlag\": \"Y\",\r\n" + "    \"bankCode\": \"\",\r\n"
				+ "    \"cardDeductStatus\": \"000\",\r\n" + "    \"contNo\": \"" + policyNo + "\",\r\n"
				+ "    \"cvaliDate\": \"20210722\",\r\n" + "    \"docType\": \"2\",\r\n"
				+ "    \"endDate\": \"20410722\",\r\n" + "    \"newPayMode\": \"3\",\r\n"
				+ "    \"payMode\": \"3\",\r\n" + "    \"payTermType\": \"I\",\r\n"
				+ "    \"receiveDt\": \"20210722\",\r\n" + "    \"receiveSuccessYn\": \"N\",\r\n"
				+ "    \"sendTypeAllRisk\": \"\",\r\n" + "    \"source\": \"3\",\r\n" + "    \"status\": \"P\",\r\n"
				+ "    \"subBankCode\": \"\"\r\n" + "  },\r\n" + "  \"lcDisabledInfoList\": [\r\n" + "    {\r\n"
				+ "      \"customerName\": \"\",\r\n" + "      \"disabledLevel\": \"\",\r\n"
				+ "      \"disabledType\": \"\",\r\n" + "      \"icdType\": \"\",\r\n" + "      \"idNo\": \"\",\r\n"
				+ "      \"idType\": \"\"\r\n" + "    }\r\n" + "  ],\r\n" + "  \"lcFinancialnNotice\": {\r\n"
				+ "    \"appntIdNo\": \"V120927298\",\r\n" + "    \"appntName\": \"汪透意\",\r\n"
				+ "    \"impartList\": [\r\n" + "      {\r\n" + "        \"impartCode\": \"D5001\",\r\n"
				+ "        \"impartContent\": \"100\",\r\n" + "        \"impartOption\": \"\",\r\n"
				+ "        \"impartVer\": \"D50\"\r\n" + "      },\r\n" + "      {\r\n"
				+ "        \"impartCode\": \"D5002\",\r\n" + "        \"impartContent\": \"200\",\r\n"
				+ "        \"impartOption\": \"\",\r\n" + "        \"impartVer\": \"D50\"\r\n" + "      },\r\n"
				+ "      {\r\n" + "        \"impartCode\": \"D3001\",\r\n" + "        \"impartContent\": \"\",\r\n"
				+ "        \"impartOption\": \"Y\",\r\n" + "        \"impartVer\": \"D30\"\r\n" + "      },\r\n"
				+ "      {\r\n" + "        \"impartCode\": \"D3002\",\r\n" + "        \"impartContent\": \"\",\r\n"
				+ "        \"impartOption\": \"N\",\r\n" + "        \"impartVer\": \"D30\"\r\n" + "      },\r\n"
				+ "      {\r\n" + "        \"impartCode\": \"D3003\",\r\n" + "        \"impartContent\": \"\",\r\n"
				+ "        \"impartOption\": \"N\",\r\n" + "        \"impartVer\": \"D30\"\r\n" + "      },\r\n"
				+ "      {\r\n" + "        \"impartCode\": \"D3004\",\r\n" + "        \"impartContent\": \"\",\r\n"
				+ "        \"impartOption\": \"N\",\r\n" + "        \"impartVer\": \"D30\"\r\n" + "      },\r\n"
				+ "      {\r\n" + "        \"impartCode\": \"D3005\",\r\n" + "        \"impartContent\": \"\",\r\n"
				+ "        \"impartOption\": \"N\",\r\n" + "        \"impartVer\": \"D30\"\r\n" + "      },\r\n"
				+ "      {\r\n" + "        \"impartCode\": \"D3006\",\r\n" + "        \"impartContent\": \"\",\r\n"
				+ "        \"impartOption\": \"N\",\r\n" + "        \"impartVer\": \"D30\"\r\n" + "      },\r\n"
				+ "      {\r\n" + "        \"impartCode\": \"D3007\",\r\n" + "        \"impartContent\": \"\",\r\n"
				+ "        \"impartOption\": \"N\",\r\n" + "        \"impartVer\": \"D30\"\r\n" + "      }\r\n"
				+ "    ]\r\n" + "  },\r\n" + "  \"lcInsuredList\": [\r\n" + "    {\r\n"
				+ "      \"lcInsuredSchema\": [\r\n" + "        {\r\n" + "          \"avoirdupois\": \"75.0\",\r\n"
				+ "          \"birthday\": \"19990101\",\r\n" + "          \"city\": \"金門縣\",\r\n"
				+ "          \"custodyFlag\": \"N\",\r\n" + "          \"description\": \"金門縣金寧鄉中山路２０號\",\r\n"
				+ "          \"disabilityFlag\": \"N\",\r\n" + "          \"district\": \"金寧鄉\",\r\n"
				+ "          \"email\": \"yen-chun.kao@taiwanlife.com\",\r\n" + "          \"homePhone\": \"\",\r\n"
				+ "          \"idNo\": \"V120927298\",\r\n" + "          \"idType\": \"1\",\r\n"
				+ "          \"insuredAppAge\": \"23\",\r\n" + "          \"interestedPartyYn\": \"N\",\r\n"
				+ "          \"mobile\": \"0999888877\",\r\n" + "          \"name\": \"汪透意\",\r\n"
				+ "          \"nativePlace\": \"TW\",\r\n" + "          \"occupationCode\": \"16010010\",\r\n"
				+ "          \"occupationType\": \"1\",\r\n" + "          \"partCode\": \"\",\r\n"
				+ "          \"partType\": \"\",\r\n" + "          \"permanentCountry\": \"TW\",\r\n"
				+ "          \"relationToMainInsured\": \"1\",\r\n" + "          \"sex\": \"M\",\r\n"
				+ "          \"stature\": \"180\",\r\n" + "          \"zipCode\": \"892\"\r\n" + "        }\r\n"
				+ "      ]\r\n" + "    }\r\n" + "  ],\r\n" + "  \"lcPostAddress\": {\r\n" + "    \"posType\": \"\"\r\n"
				+ "  },\r\n" + "  \"lcReviewInstructionList\": [\r\n" + "    {\r\n"
				+ "      \"provisionDate\": \"20210722\",\r\n" + "      \"reviewStatement\": \"0\",\r\n"
				+ "      \"riskCode\": \"NTTL0203\",\r\n" + "      \"statementDate\": \"20210722\"\r\n" + "    }\r\n"
				+ "  ],\r\n" + "  \"lcamluwResult\": {\r\n" + "    \"amlYn\": \"N\",\r\n"
				+ "    \"countDate\": \"2021-07-22 09:39:44\",\r\n" + "    \"countLevel\": \"M\",\r\n"
				+ "    \"decBy\": \"\",\r\n" + "    \"decDate\": \"\",\r\n" + "    \"decState\": \"NOHIT\",\r\n"
				+ "    \"decType\": \"\",\r\n" + "    \"nblcRetCode\": \"0\",\r\n" + "    \"nblcRetMsg\": \"\",\r\n"
				+ "    \"score\": \"52.00\"\r\n" + "  },\r\n" + "  \"lcpolList\": [\r\n" + "    {\r\n"
				+ "      \"amnt\": \"500000\",\r\n" + "      \"anPay\": {\r\n" + "        \"account\": \"\",\r\n"
				+ "        \"accountName\": \"汪透意\",\r\n" + "        \"bankCode\": \"\",\r\n"
				+ "        \"guaryGetMode\": \"\"\r\n" + "      },\r\n" + "      \"annyAmtYn\": \"\",\r\n"
				+ "      \"endDate\": \"20410722\",\r\n" + "      \"getStartDate\": \"\",\r\n"
				+ "      \"getYear\": \"\",\r\n" + "      \"getintv\": \"\",\r\n" + "      \"guarYear\": \"\",\r\n"
				+ "      \"idNo\": \"V120927298\",\r\n" + "      \"idType\": \"1\",\r\n"
				+ "      \"insuredBirthday\": \"19990101\",\r\n" + "      \"insuredName\": \"汪透意\",\r\n"
				+ "      \"insuredSex\": \"M\",\r\n" + "      \"kind\": \"1\",\r\n" + "      \"lcDutyList\": [\r\n"
				+ "        {\r\n" + "          \"amnt\": \"500000\",\r\n" + "          \"prem\": \"1080.00\"\r\n"
				+ "        }\r\n" + "      ],\r\n" + "      \"mainFaceAmt\": \"500000\",\r\n"
				+ "      \"payIntv\": \"12\",\r\n" + "      \"payYears\": \"20\",\r\n"
				+ "      \"prem\": \"1080.00\",\r\n" + "      \"relationCode\": \"5\",\r\n"
				+ "      \"riskCode\": \"NTTL0203\",\r\n" + "      \"standPrem\": \"1080.00\"\r\n" + "    },\r\n"
				+ "    {\r\n" + "      \"amnt\": \"500000\",\r\n" + "      \"anPay\": {\r\n"
				+ "        \"account\": \"\",\r\n" + "        \"accountName\": \"汪透意\",\r\n"
				+ "        \"bankCode\": \"\",\r\n" + "        \"guaryGetMode\": \"\"\r\n" + "      },\r\n"
				+ "      \"annyAmtYn\": \"\",\r\n" + "      \"endDate\": \"20410722\",\r\n"
				+ "      \"getStartDate\": \"\",\r\n" + "      \"getYear\": \"\",\r\n" + "      \"getintv\": \"\",\r\n"
				+ "      \"guarYear\": \"\",\r\n" + "      \"idNo\": \"V120927298\",\r\n"
				+ "      \"idType\": \"1\",\r\n" + "      \"insuredBirthday\": \"19990101\",\r\n"
				+ "      \"insuredName\": \"汪透意\",\r\n" + "      \"insuredSex\": \"M\",\r\n"
				+ "      \"kind\": \"2\",\r\n" + "      \"lcDutyList\": [\r\n" + "        {\r\n"
				+ "          \"amnt\": \"500000\",\r\n" + "          \"prem\": \"215.00\"\r\n" + "        }\r\n"
				+ "      ],\r\n" + "      \"mainFaceAmt\": \"500000\",\r\n" + "      \"payIntv\": \"12\",\r\n"
				+ "      \"payYears\": \"1\",\r\n" + "      \"prem\": \"215.00\",\r\n"
				+ "      \"relationCode\": \"5\",\r\n" + "      \"riskCode\": \"NTPA0502\",\r\n"
				+ "      \"standPrem\": \"215.00\"\r\n" + "    }\r\n" + "  ],\r\n" + "  \"liaInsuredList\": [\r\n"
				+ "    {\r\n" + "      \"liaInsured\": {\r\n" + "        \"askBirDate\": \"00880101\",\r\n"
				+ "        \"askIdNo\": \"V120927298          \",\r\n" + "        \"askName\": \"汪透意\",\r\n"
				+ "        \"askType\": \"01\",\r\n" + "        \"bamtType\": \"2\",\r\n"
				+ "        \"birDate\": \"00880101\",\r\n" + "        \"brokType\": \"\",\r\n"
				+ "        \"channel\": \"1\",\r\n" + "        \"cmpNo\": \"02\",\r\n"
				+ "        \"cmpType\": \"L\",\r\n" + "        \"con\": \"01\",\r\n"
				+ "        \"conDate\": \"01100722\",\r\n" + "        \"conTime\": \"0000\",\r\n"
				+ "        \"dataserNo\": \"1\",\r\n" + "        \"fillDate\": \"        \",\r\n"
				+ "        \"idNo\": \"V120927298          \",\r\n" + "        \"insClass\": \"1\",\r\n"
				+ "        \"insItem\": \"19\",\r\n" + "        \"insKind\": \"1\",\r\n"
				+ "        \"insNo\": \"9000103126-01\",\r\n" + "        \"insNom\": \"9000103126\",\r\n"
				+ "        \"isSuccess\": \"Y\",\r\n" + "        \"itemA\": \"500000\",\r\n"
				+ "        \"itemB\": \"500000\",\r\n" + "        \"itemC\": \"0\",\r\n"
				+ "        \"itemD\": \"0\",\r\n" + "        \"itemE\": \"0\",\r\n" + "        \"itemF\": \"0\",\r\n"
				+ "        \"itemG\": \"0\",\r\n" + "        \"itemH\": \"0\",\r\n" + "        \"itemI\": \"0\",\r\n"
				+ "        \"itemJ\": \"0\",\r\n" + "        \"itemK\": \"0\",\r\n" + "        \"itemL\": \"0\",\r\n"
				+ "        \"itemM\": \"0\",\r\n" + "        \"itemN\": \"0\",\r\n" + "        \"itemO\": \"0\",\r\n"
				+ "        \"itemP\": \"0\",\r\n" + "        \"itemQ\": \"0\",\r\n" + "        \"itemR\": \"0\",\r\n"
				+ "        \"itemS\": \"0\",\r\n" + "        \"name\": \"汪透意\",\r\n" + "        \"origin\": \"0\",\r\n"
				+ "        \"ovrDate\": \"01300722\",\r\n" + "        \"ovrTime\": \"0000\",\r\n"
				+ "        \"payType\": \"0\",\r\n" + "        \"prdCode\": \"20T04T1\",\r\n"
				+ "        \"prm\": \"1080\",\r\n" + "        \"prmYears\": \"20\",\r\n" + "        \"sex\": \"1\",\r\n"
				+ "        \"valDate\": \"01100722\",\r\n" + "        \"valTime\": \"0000\"\r\n" + "      }\r\n"
				+ "    },\r\n" + "    {\r\n" + "      \"liaInsured\": {\r\n"
				+ "        \"askBirDate\": \"00880101\",\r\n" + "        \"askIdNo\": \"V120927298          \",\r\n"
				+ "        \"askName\": \"汪透意\",\r\n" + "        \"askType\": \"01\",\r\n"
				+ "        \"bamtType\": \"2\",\r\n" + "        \"birDate\": \"00880101\",\r\n"
				+ "        \"brokType\": \"\",\r\n" + "        \"channel\": \"1\",\r\n"
				+ "        \"cmpNo\": \"02\",\r\n" + "        \"cmpType\": \"L\",\r\n" + "        \"con\": \"01\",\r\n"
				+ "        \"conDate\": \"01100722\",\r\n" + "        \"conTime\": \"0000\",\r\n"
				+ "        \"dataserNo\": \"2\",\r\n" + "        \"fillDate\": \"        \",\r\n"
				+ "        \"idNo\": \"V120927298          \",\r\n" + "        \"insClass\": \"1\",\r\n"
				+ "        \"insItem\": \"01\",\r\n" + "        \"insKind\": \"2\",\r\n"
				+ "        \"insNo\": \"9000103126-02\",\r\n" + "        \"insNom\": \"9000103126\",\r\n"
				+ "        \"isSuccess\": \"Y\",\r\n" + "        \"itemA\": \"600000\",\r\n"
				+ "        \"itemB\": \"0\",\r\n" + "        \"itemC\": \"0\",\r\n" + "        \"itemD\": \"0\",\r\n"
				+ "        \"itemE\": \"0\",\r\n" + "        \"itemF\": \"0\",\r\n" + "        \"itemG\": \"0\",\r\n"
				+ "        \"itemH\": \"0\",\r\n" + "        \"itemI\": \"0\",\r\n" + "        \"itemJ\": \"0\",\r\n"
				+ "        \"itemK\": \"0\",\r\n" + "        \"itemL\": \"0\",\r\n" + "        \"itemM\": \"0\",\r\n"
				+ "        \"itemN\": \"0\",\r\n" + "        \"itemO\": \"0\",\r\n" + "        \"itemP\": \"0\",\r\n"
				+ "        \"itemQ\": \"0\",\r\n" + "        \"itemR\": \"0\",\r\n" + "        \"itemS\": \"0\",\r\n"
				+ "        \"name\": \"汪透意\",\r\n" + "        \"origin\": \"0\",\r\n"
				+ "        \"ovrDate\": \"01300722\",\r\n" + "        \"ovrTime\": \"0000\",\r\n"
				+ "        \"payType\": \"0\",\r\n" + "        \"prdCode\": \"TADR7\",\r\n"
				+ "        \"prm\": \"215\",\r\n" + "        \"prmYears\": \"62\",\r\n" + "        \"sex\": \"1\",\r\n"
				+ "        \"valDate\": \"01100722\",\r\n" + "        \"valTime\": \"0000\"\r\n" + "      }\r\n"
				+ "    },\r\n" + "    {\r\n" + "      \"liaInsured\": {\r\n"
				+ "        \"askBirDate\": \"00880101\",\r\n" + "        \"askIdNo\": \"V120927298          \",\r\n"
				+ "        \"askName\": \"汪透意\",\r\n" + "        \"askType\": \"01\",\r\n"
				+ "        \"bamtType\": \"2\",\r\n" + "        \"birDate\": \"00880101\",\r\n"
				+ "        \"brokType\": \"00\",\r\n" + "        \"channel\": \"1\",\r\n"
				+ "        \"cmpNo\": \"02\",\r\n" + "        \"cmpType\": \"R\",\r\n" + "        \"con\": \"01\",\r\n"
				+ "        \"conDate\": \"01100722\",\r\n" + "        \"conTime\": \"0000\",\r\n"
				+ "        \"dataserNo\": \"1\",\r\n" + "        \"fillDate\": \"01100722\",\r\n"
				+ "        \"idNo\": \"V120927298          \",\r\n" + "        \"insClass\": \"1\",\r\n"
				+ "        \"insItem\": \"19\",\r\n" + "        \"insKind\": \"1\",\r\n"
				+ "        \"insNo\": \"9000103126-01\",\r\n" + "        \"insNom\": \"9000103126\",\r\n"
				+ "        \"isSuccess\": \"Y\",\r\n" + "        \"itemA\": \"500000\",\r\n"
				+ "        \"itemB\": \"500000\",\r\n" + "        \"itemC\": \"0\",\r\n"
				+ "        \"itemD\": \"0\",\r\n" + "        \"itemE\": \"0\",\r\n" + "        \"itemF\": \"0\",\r\n"
				+ "        \"itemG\": \"0\",\r\n" + "        \"itemH\": \"0\",\r\n" + "        \"itemI\": \"0\",\r\n"
				+ "        \"itemJ\": \"0\",\r\n" + "        \"itemK\": \"0\",\r\n" + "        \"itemL\": \"0\",\r\n"
				+ "        \"itemM\": \"0\",\r\n" + "        \"itemN\": \"0\",\r\n" + "        \"itemO\": \"0\",\r\n"
				+ "        \"itemP\": \"0\",\r\n" + "        \"itemQ\": \"0\",\r\n" + "        \"itemR\": \"0\",\r\n"
				+ "        \"itemS\": \"0\",\r\n" + "        \"name\": \"汪透意\",\r\n" + "        \"origin\": \"0\",\r\n"
				+ "        \"ovrDate\": \"01300722\",\r\n" + "        \"ovrTime\": \"0000\",\r\n"
				+ "        \"payType\": \"0\",\r\n" + "        \"prdCode\": \"20T04T1\",\r\n"
				+ "        \"prm\": \"1080\",\r\n" + "        \"prmYears\": \"20\",\r\n" + "        \"sex\": \"1\",\r\n"
				+ "        \"valDate\": \"01100722\",\r\n" + "        \"valTime\": \"0000\"\r\n" + "      }\r\n"
				+ "    },\r\n" + "    {\r\n" + "      \"liaInsured\": {\r\n"
				+ "        \"askBirDate\": \"00880101\",\r\n" + "        \"askIdNo\": \"V120927298          \",\r\n"
				+ "        \"askName\": \"汪透意\",\r\n" + "        \"askType\": \"01\",\r\n"
				+ "        \"bamtType\": \"2\",\r\n" + "        \"birDate\": \"00880101\",\r\n"
				+ "        \"brokType\": \"00\",\r\n" + "        \"channel\": \"1\",\r\n"
				+ "        \"cmpNo\": \"02\",\r\n" + "        \"cmpType\": \"R\",\r\n" + "        \"con\": \"01\",\r\n"
				+ "        \"conDate\": \"01100722\",\r\n" + "        \"conTime\": \"0000\",\r\n"
				+ "        \"dataserNo\": \"2\",\r\n" + "        \"fillDate\": \"01100722\",\r\n"
				+ "        \"idNo\": \"V120927298          \",\r\n" + "        \"insClass\": \"1\",\r\n"
				+ "        \"insItem\": \"01\",\r\n" + "        \"insKind\": \"2\",\r\n"
				+ "        \"insNo\": \"9000103126-02\",\r\n" + "        \"insNom\": \"9000103126\",\r\n"
				+ "        \"isSuccess\": \"Y\",\r\n" + "        \"itemA\": \"600000\",\r\n"
				+ "        \"itemB\": \"0\",\r\n" + "        \"itemC\": \"0\",\r\n" + "        \"itemD\": \"0\",\r\n"
				+ "        \"itemE\": \"0\",\r\n" + "        \"itemF\": \"0\",\r\n" + "        \"itemG\": \"0\",\r\n"
				+ "        \"itemH\": \"0\",\r\n" + "        \"itemI\": \"0\",\r\n" + "        \"itemJ\": \"0\",\r\n"
				+ "        \"itemK\": \"0\",\r\n" + "        \"itemL\": \"0\",\r\n" + "        \"itemM\": \"0\",\r\n"
				+ "        \"itemN\": \"0\",\r\n" + "        \"itemO\": \"0\",\r\n" + "        \"itemP\": \"0\",\r\n"
				+ "        \"itemQ\": \"0\",\r\n" + "        \"itemR\": \"0\",\r\n" + "        \"itemS\": \"0\",\r\n"
				+ "        \"name\": \"汪透意\",\r\n" + "        \"origin\": \"0\",\r\n"
				+ "        \"ovrDate\": \"01300722\",\r\n" + "        \"ovrTime\": \"0000\",\r\n"
				+ "        \"payType\": \"0\",\r\n" + "        \"prdCode\": \"TADR7\",\r\n"
				+ "        \"prm\": \"215\",\r\n" + "        \"prmYears\": \"62\",\r\n" + "        \"sex\": \"1\",\r\n"
				+ "        \"valDate\": \"01100722\",\r\n" + "        \"valTime\": \"0000\"\r\n" + "      }\r\n"
				+ "    }\r\n" + "  ],\r\n" + "  \"liaQueryList\": [\r\n" + "    {\r\n" + "      \"liaQuerie\": {\r\n"
				+ "        \"askBirDate\": \"\",\r\n" + "        \"askIdNo\": \"\",\r\n"
				+ "        \"askName\": \"\",\r\n" + "        \"askType\": \"\",\r\n"
				+ "        \"bamtType\": \"\",\r\n" + "        \"birDate\": \"\",\r\n"
				+ "        \"brokType\": \"\",\r\n" + "        \"channel\": \"\",\r\n" + "        \"cmpNo\": \"\",\r\n"
				+ "        \"cmpType\": \"\",\r\n" + "        \"con\": \"\",\r\n" + "        \"conDate\": \"\",\r\n"
				+ "        \"conTime\": \"\",\r\n" + "        \"dataserNo\": \"\",\r\n"
				+ "        \"fillDate\": \"\",\r\n" + "        \"idNno\": \"\",\r\n" + "        \"insClass\": \"\",\r\n"
				+ "        \"insItem\": \"\",\r\n" + "        \"insKind\": \"\",\r\n" + "        \"insNo\": \"\",\r\n"
				+ "        \"insNom\": \"\",\r\n" + "        \"itemA\": \"\",\r\n" + "        \"itemB\": \"\",\r\n"
				+ "        \"itemC\": \"\",\r\n" + "        \"itemD\": \"\",\r\n" + "        \"itemE\": \"\",\r\n"
				+ "        \"itemF\": \"\",\r\n" + "        \"itemG\": \"\",\r\n" + "        \"itemH\": \"\",\r\n"
				+ "        \"itemI\": \"\",\r\n" + "        \"itemJ\": \"\",\r\n" + "        \"itemK\": \"\",\r\n"
				+ "        \"itemL\": \"\",\r\n" + "        \"itemM\": \"\",\r\n" + "        \"itemN\": \"\",\r\n"
				+ "        \"itemO\": \"\",\r\n" + "        \"itemP\": \"\",\r\n" + "        \"itemQ\": \"\",\r\n"
				+ "        \"itemR\": \"\",\r\n" + "        \"itemS\": \"\",\r\n" + "        \"name\": \"\",\r\n"
				+ "        \"origin\": \"\",\r\n" + "        \"ovrDate\": \"\",\r\n" + "        \"ovrTime\": \"\",\r\n"
				+ "        \"payType\": \"\",\r\n" + "        \"prdCode\": \"\",\r\n" + "        \"prm\": \"\",\r\n"
				+ "        \"prmYears\": \"\",\r\n" + "        \"sex\": \"\",\r\n" + "        \"valDate\": \"\",\r\n"
				+ "        \"valTime\": \"\"\r\n" + "      }\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"liaQuerie\": {\r\n" + "        \"askBirDate\": \"\",\r\n" + "        \"askIdNo\": \"\",\r\n"
				+ "        \"askName\": \"\",\r\n" + "        \"askType\": \"\",\r\n"
				+ "        \"bamtType\": \"\",\r\n" + "        \"birDate\": \"\",\r\n"
				+ "        \"brokType\": \"\",\r\n" + "        \"channel\": \"\",\r\n" + "        \"cmpNo\": \"\",\r\n"
				+ "        \"cmpType\": \"\",\r\n" + "        \"con\": \"\",\r\n" + "        \"conDate\": \"\",\r\n"
				+ "        \"conTime\": \"\",\r\n" + "        \"dataserNo\": \"\",\r\n"
				+ "        \"fillDate\": \"\",\r\n" + "        \"idNno\": \"\",\r\n" + "        \"insClass\": \"\",\r\n"
				+ "        \"insItem\": \"\",\r\n" + "        \"insKind\": \"\",\r\n" + "        \"insNo\": \"\",\r\n"
				+ "        \"insNom\": \"\",\r\n" + "        \"itemA\": \"\",\r\n" + "        \"itemB\": \"\",\r\n"
				+ "        \"itemC\": \"\",\r\n" + "        \"itemD\": \"\",\r\n" + "        \"itemE\": \"\",\r\n"
				+ "        \"itemF\": \"\",\r\n" + "        \"itemG\": \"\",\r\n" + "        \"itemH\": \"\",\r\n"
				+ "        \"itemI\": \"\",\r\n" + "        \"itemJ\": \"\",\r\n" + "        \"itemK\": \"\",\r\n"
				+ "        \"itemL\": \"\",\r\n" + "        \"itemM\": \"\",\r\n" + "        \"itemN\": \"\",\r\n"
				+ "        \"itemO\": \"\",\r\n" + "        \"itemP\": \"\",\r\n" + "        \"itemQ\": \"\",\r\n"
				+ "        \"itemR\": \"\",\r\n" + "        \"itemS\": \"\",\r\n" + "        \"name\": \"\",\r\n"
				+ "        \"origin\": \"\",\r\n" + "        \"ovrDate\": \"\",\r\n" + "        \"ovrTime\": \"\",\r\n"
				+ "        \"payType\": \"\",\r\n" + "        \"prdCode\": \"\",\r\n" + "        \"prm\": \"\",\r\n"
				+ "        \"prmYears\": \"\",\r\n" + "        \"sex\": \"\",\r\n" + "        \"valDate\": \"\",\r\n"
				+ "        \"valTime\": \"\"\r\n" + "      }\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"liaQuerie\": {\r\n" + "        \"askBirDate\": \"\",\r\n" + "        \"askIdNo\": \"\",\r\n"
				+ "        \"askName\": \"\",\r\n" + "        \"askType\": \"\",\r\n"
				+ "        \"bamtType\": \"\",\r\n" + "        \"birDate\": \"\",\r\n"
				+ "        \"brokType\": \"\",\r\n" + "        \"channel\": \"\",\r\n" + "        \"cmpNo\": \"\",\r\n"
				+ "        \"cmpType\": \"\",\r\n" + "        \"con\": \"\",\r\n" + "        \"conDate\": \"\",\r\n"
				+ "        \"conTime\": \"\",\r\n" + "        \"dataserNo\": \"\",\r\n"
				+ "        \"fillDate\": \"\",\r\n" + "        \"idNno\": \"\",\r\n" + "        \"insClass\": \"\",\r\n"
				+ "        \"insItem\": \"\",\r\n" + "        \"insKind\": \"\",\r\n" + "        \"insNo\": \"\",\r\n"
				+ "        \"insNom\": \"\",\r\n" + "        \"itemA\": \"\",\r\n" + "        \"itemB\": \"\",\r\n"
				+ "        \"itemC\": \"\",\r\n" + "        \"itemD\": \"\",\r\n" + "        \"itemE\": \"\",\r\n"
				+ "        \"itemF\": \"\",\r\n" + "        \"itemG\": \"\",\r\n" + "        \"itemH\": \"\",\r\n"
				+ "        \"itemI\": \"\",\r\n" + "        \"itemJ\": \"\",\r\n" + "        \"itemK\": \"\",\r\n"
				+ "        \"itemL\": \"\",\r\n" + "        \"itemM\": \"\",\r\n" + "        \"itemN\": \"\",\r\n"
				+ "        \"itemO\": \"\",\r\n" + "        \"itemP\": \"\",\r\n" + "        \"itemQ\": \"\",\r\n"
				+ "        \"itemR\": \"\",\r\n" + "        \"itemS\": \"\",\r\n" + "        \"name\": \"\",\r\n"
				+ "        \"origin\": \"\",\r\n" + "        \"ovrDate\": \"\",\r\n" + "        \"ovrTime\": \"\",\r\n"
				+ "        \"payType\": \"\",\r\n" + "        \"prdCode\": \"\",\r\n" + "        \"prm\": \"\",\r\n"
				+ "        \"prmYears\": \"\",\r\n" + "        \"sex\": \"\",\r\n" + "        \"valDate\": \"\",\r\n"
				+ "        \"valTime\": \"\"\r\n" + "      }\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"liaQuerie\": {\r\n" + "        \"askBirDate\": \"\",\r\n" + "        \"askIdNo\": \"\",\r\n"
				+ "        \"askName\": \"\",\r\n" + "        \"askType\": \"\",\r\n"
				+ "        \"bamtType\": \"\",\r\n" + "        \"birDate\": \"\",\r\n"
				+ "        \"brokType\": \"\",\r\n" + "        \"channel\": \"\",\r\n" + "        \"cmpNo\": \"\",\r\n"
				+ "        \"cmpType\": \"\",\r\n" + "        \"con\": \"\",\r\n" + "        \"conDate\": \"\",\r\n"
				+ "        \"conTime\": \"\",\r\n" + "        \"dataserNo\": \"\",\r\n"
				+ "        \"fillDate\": \"\",\r\n" + "        \"idNno\": \"\",\r\n" + "        \"insClass\": \"\",\r\n"
				+ "        \"insItem\": \"\",\r\n" + "        \"insKind\": \"\",\r\n" + "        \"insNo\": \"\",\r\n"
				+ "        \"insNom\": \"\",\r\n" + "        \"itemA\": \"\",\r\n" + "        \"itemB\": \"\",\r\n"
				+ "        \"itemC\": \"\",\r\n" + "        \"itemD\": \"\",\r\n" + "        \"itemE\": \"\",\r\n"
				+ "        \"itemF\": \"\",\r\n" + "        \"itemG\": \"\",\r\n" + "        \"itemH\": \"\",\r\n"
				+ "        \"itemI\": \"\",\r\n" + "        \"itemJ\": \"\",\r\n" + "        \"itemK\": \"\",\r\n"
				+ "        \"itemL\": \"\",\r\n" + "        \"itemM\": \"\",\r\n" + "        \"itemN\": \"\",\r\n"
				+ "        \"itemO\": \"\",\r\n" + "        \"itemP\": \"\",\r\n" + "        \"itemQ\": \"\",\r\n"
				+ "        \"itemR\": \"\",\r\n" + "        \"itemS\": \"\",\r\n" + "        \"name\": \"\",\r\n"
				+ "        \"origin\": \"\",\r\n" + "        \"ovrDate\": \"\",\r\n" + "        \"ovrTime\": \"\",\r\n"
				+ "        \"payType\": \"\",\r\n" + "        \"prdCode\": \"\",\r\n" + "        \"prm\": \"\",\r\n"
				+ "        \"prmYears\": \"\",\r\n" + "        \"sex\": \"\",\r\n" + "        \"valDate\": \"\",\r\n"
				+ "        \"valTime\": \"\"\r\n" + "      }\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"liaQuerie\": {\r\n" + "        \"askBirDate\": \"\",\r\n" + "        \"askIdNo\": \"\",\r\n"
				+ "        \"askName\": \"\",\r\n" + "        \"askType\": \"\",\r\n"
				+ "        \"bamtType\": \"\",\r\n" + "        \"birDate\": \"\",\r\n"
				+ "        \"brokType\": \"\",\r\n" + "        \"channel\": \"\",\r\n" + "        \"cmpNo\": \"\",\r\n"
				+ "        \"cmpType\": \"\",\r\n" + "        \"con\": \"\",\r\n" + "        \"conDate\": \"\",\r\n"
				+ "        \"conTime\": \"\",\r\n" + "        \"dataserNo\": \"\",\r\n"
				+ "        \"fillDate\": \"\",\r\n" + "        \"idNno\": \"\",\r\n" + "        \"insClass\": \"\",\r\n"
				+ "        \"insItem\": \"\",\r\n" + "        \"insKind\": \"\",\r\n" + "        \"insNo\": \"\",\r\n"
				+ "        \"insNom\": \"\",\r\n" + "        \"itemA\": \"\",\r\n" + "        \"itemB\": \"\",\r\n"
				+ "        \"itemC\": \"\",\r\n" + "        \"itemD\": \"\",\r\n" + "        \"itemE\": \"\",\r\n"
				+ "        \"itemF\": \"\",\r\n" + "        \"itemG\": \"\",\r\n" + "        \"itemH\": \"\",\r\n"
				+ "        \"itemI\": \"\",\r\n" + "        \"itemJ\": \"\",\r\n" + "        \"itemK\": \"\",\r\n"
				+ "        \"itemL\": \"\",\r\n" + "        \"itemM\": \"\",\r\n" + "        \"itemN\": \"\",\r\n"
				+ "        \"itemO\": \"\",\r\n" + "        \"itemP\": \"\",\r\n" + "        \"itemQ\": \"\",\r\n"
				+ "        \"itemR\": \"\",\r\n" + "        \"itemS\": \"\",\r\n" + "        \"name\": \"\",\r\n"
				+ "        \"origin\": \"\",\r\n" + "        \"ovrDate\": \"\",\r\n" + "        \"ovrTime\": \"\",\r\n"
				+ "        \"payType\": \"\",\r\n" + "        \"prdCode\": \"\",\r\n" + "        \"prm\": \"\",\r\n"
				+ "        \"prmYears\": \"\",\r\n" + "        \"sex\": \"\",\r\n" + "        \"valDate\": \"\",\r\n"
				+ "        \"valTime\": \"\"\r\n" + "      }\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"liaQuerie\": {\r\n" + "        \"askBirDate\": \"\",\r\n" + "        \"askIdNo\": \"\",\r\n"
				+ "        \"askName\": \"\",\r\n" + "        \"askType\": \"\",\r\n"
				+ "        \"bamtType\": \"\",\r\n" + "        \"birDate\": \"\",\r\n"
				+ "        \"brokType\": \"\",\r\n" + "        \"channel\": \"\",\r\n" + "        \"cmpNo\": \"\",\r\n"
				+ "        \"cmpType\": \"\",\r\n" + "        \"con\": \"\",\r\n" + "        \"conDate\": \"\",\r\n"
				+ "        \"conTime\": \"\",\r\n" + "        \"dataserNo\": \"\",\r\n"
				+ "        \"fillDate\": \"\",\r\n" + "        \"idNno\": \"\",\r\n" + "        \"insClass\": \"\",\r\n"
				+ "        \"insItem\": \"\",\r\n" + "        \"insKind\": \"\",\r\n" + "        \"insNo\": \"\",\r\n"
				+ "        \"insNom\": \"\",\r\n" + "        \"itemA\": \"\",\r\n" + "        \"itemB\": \"\",\r\n"
				+ "        \"itemC\": \"\",\r\n" + "        \"itemD\": \"\",\r\n" + "        \"itemE\": \"\",\r\n"
				+ "        \"itemF\": \"\",\r\n" + "        \"itemG\": \"\",\r\n" + "        \"itemH\": \"\",\r\n"
				+ "        \"itemI\": \"\",\r\n" + "        \"itemJ\": \"\",\r\n" + "        \"itemK\": \"\",\r\n"
				+ "        \"itemL\": \"\",\r\n" + "        \"itemM\": \"\",\r\n" + "        \"itemN\": \"\",\r\n"
				+ "        \"itemO\": \"\",\r\n" + "        \"itemP\": \"\",\r\n" + "        \"itemQ\": \"\",\r\n"
				+ "        \"itemR\": \"\",\r\n" + "        \"itemS\": \"\",\r\n" + "        \"name\": \"\",\r\n"
				+ "        \"origin\": \"\",\r\n" + "        \"ovrDate\": \"\",\r\n" + "        \"ovrTime\": \"\",\r\n"
				+ "        \"payType\": \"\",\r\n" + "        \"prdCode\": \"\",\r\n" + "        \"prm\": \"\",\r\n"
				+ "        \"prmYears\": \"\",\r\n" + "        \"sex\": \"\",\r\n" + "        \"valDate\": \"\",\r\n"
				+ "        \"valTime\": \"\"\r\n" + "      }\r\n" + "    }\r\n" + "  ],\r\n" + "  \"ljtempfee\": {\r\n"
				+ "    \"payDate\": \"2021-07-22\",\r\n" + "    \"payMoney\": \"1295.00\"\r\n" + "  },\r\n"
				+ "  \"statementItems\": {\r\n" + "    \"statementItems\": [\r\n" + "      {\r\n"
				+ "        \"impartCode\": \"Y0101\",\r\n" + "        \"impartOption\": \"Y\",\r\n"
				+ "        \"impartVer\": \"Y01\"\r\n" + "      }\r\n" + "    ]\r\n" + "  },\r\n"
				+ "  \"transferInfo\": {\r\n" + "    \"authorizer\": \"\",\r\n" + "    \"authorizerID\": \"\",\r\n"
				+ "    \"authorizerIndicator\": \"\",\r\n" + "    \"paymentAccount\": \"\",\r\n"
				+ "    \"paymentBankCode\": \"\"\r\n" + "  }\r\n" + "}";

	}

}
