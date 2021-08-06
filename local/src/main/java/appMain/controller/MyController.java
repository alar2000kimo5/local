package appMain.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import appMain.jpa.service.CityService;
import appMain.service.Myservice;
import appMain.service.myInterface;

@RestController
@RequestMapping(value = "/my")
public class MyController {

	//private static Logger logger = Logger.getLogger(MyController.class);
	private final Logger logger = LoggerFactory.getLogger(getClass()); // slf4j
	
	@Autowired
	private Myservice myservice;

	@Autowired
	private myInterface interfac;

	@Autowired
	private CityService cityService;

	@GetMapping(value = "/sss")
	public String sss() {
		//interfac.init();
		return "success";
	}

	@GetMapping(value = "/getsss")
	public String getsss() {
		System.out.println("check");
		return myservice.getMyName();
	}

	@RequestMapping(value = "/sort")
	public String maping() {

		return cityService.get_CitySort();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/page/{page}&&{pagesize}")
	public String page(@PathVariable int page, @PathVariable int pagesize) {
		logger.info("RequestMethod.GET");
		String jsonString = "{\"ab\" : \"cd\"}";
		logger.info(jsonString);
		logger.debug("RequestMethod.GET");
		return cityService.get_CityPage(page, pagesize, null);
	}

	@GetMapping(value = "/acc/EstimateOrderQuerySql/Download")
	public void download(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
			throws IOException {

		InputStream inputStream = null;
		try {
			File cFile = create_File(); // 建立檔案
			BufferedWriter writer = create_writer(cFile); // 建立寫檔物件
			create_Header(writer); // 寫檔

			writer.newLine();
			writer.flush();
			set_response(response, cFile);
			inputStream = new FileInputStream(cFile);

			OutputStream out = response.getOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = inputStream.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private void set_response(HttpServletResponse response, File cFile) {
		response.reset();
		response.setContentType("application/octet-stream; charset=big5");
		response.setHeader("Content-Disposition", "attachment; filename=" + cFile.getName());
		response.setCharacterEncoding("big5");
	}

	private File create_File() throws IOException {
		// 导出路径
		File file = new File("D:/temp");// FilePathUtil.createFile("C:/temp");
		if (!file.exists()) {
			file.mkdir();
		}
		// 定义文件名格式并创建
		return File.createTempFile("EstimateOrderExcel", ".csv", file);
	}

	private BufferedWriter create_writer(File cFile) throws IOException {
		// utf_8正确读取分隔符
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cFile), "big5"), 1024);
	}

	private void create_Header(BufferedWriter writer) throws IOException {
		// 表头
		String cvsHeader[] = { "下單來源", "保單幣別", "下單日期", "基金公司", "商品(險種)代碼", "基金代碼", "下單批次", "交易型態", "買賣原因", "交易單位數",
				"保單幣金額", "匯率", "交易金額", "手續費小計", "稅小計", "保管銀行", "淨值日", "保留欄位" };
		for (int i = 0; i < cvsHeader.length; i++) {
			writer.write("" + cvsHeader[i] + "");
			if (i < (cvsHeader.length - 1)) {
				writer.write(",");
			}
		}
	}
}
