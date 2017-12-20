package online.zhaopei.myproject;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CXFClientTest {

	public static void main(String[] args) throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://171.12.5.86:83/DataInteractonWbs/webservice/wbs?wsdl");
		Object[] objects = null;
		QName opName = new QName("http://com.mh.webservice", "payParse");
		objects = client.invoke(opName, "111");
		System.out.println(objects[0].getClass());
		System.out.println(objects[0]);
	}

}
