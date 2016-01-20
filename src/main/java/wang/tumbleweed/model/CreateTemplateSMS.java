package wang.tumbleweed.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * Title: TemplateSMS
 * </p>
 * <p>
 * Description:响应XML请求的模板短信实体类
 * </p>
 */
@XmlRootElement(name = "TemplateSMS")
public class CreateTemplateSMS {


	private String appId;

	private String productType;

	private String addr;

	private String title;

	private String signature;

	private String templateContent;

	private String appendcode;

	private String content;

	private int from_type;

	private int status;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getAppendcode() {
		return appendcode;
	}

	public void setAppendcode(String appendcode) {
		this.appendcode = appendcode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFrom_type() {
		return from_type;
	}

	public void setFrom_type(int from_type) {
		this.from_type = from_type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}