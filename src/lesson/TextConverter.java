package lesson;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.json.simple.*;
import org.json.simple.parser.*;

public class TextConverter extends JFrame {
	private JButton converter;
	private JButton canceler;
	private JTextArea textIn;
	private JTextArea textOut;
	private final String CLIENT_ID = "0";
	private final String CLIENT_SECRET = "0"; 
	
	public TextConverter() {
		this.setTitle("�ؽ�Ʈ ��ȯ");
		this.setVisible(true);
		
		textIn = new JTextArea(10, 14);
		textOut = new JTextArea(10, 14);
		textIn.setLineWrap(true);
		textOut.setLineWrap(true);
		textOut.setEditable(false);
		
		JPanel textAreaPanel = new JPanel(new GridLayout(1, 2, 20, 20));
		textAreaPanel.add(textIn);
		textAreaPanel.add(textOut);
		
		converter = new JButton("��ȯ");
		canceler = new JButton("���");
		converter.addActionListener(new ButtonActionListener());
		canceler.addActionListener(new ButtonActionListener());
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(converter);
		buttonPanel.add(canceler);
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.add(BorderLayout.CENTER, textAreaPanel);
		mainPanel.add(BorderLayout.SOUTH, buttonPanel);
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		add(mainPanel);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}
	
	private class ButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == converter) {
				textOut.setText("");
				String result = null;
				try {
					result = toEnglish(textIn.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textOut.append(result);
			}
			if(e.getSource() == canceler) {
				textOut.setText("");
			}
		}
	}
	private String toEnglish(String korean) throws ParseException {
		String result = korean;
//		result = result.replace("�ؽ�Ʈ", "Text");
//		result = result.replace("����", "English");
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        String text;
        try {
            text = URLEncoder.encode(korean, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("���ڵ� ����", e);
        }

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
        requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET);

        result = post(apiURL, requestHeaders, text);
        
        result = result.substring(result.indexOf("translatedText")+"translatedText".length()+3,result.indexOf("engineType")-3);
        
        return result;
	}

	private static String post(String apiUrl, Map<String, String> requestHeaders, String text){
        HttpURLConnection con = connect(apiUrl);
        String postParams = "source=ko&target=en&text=" + text; //�������: �ѱ��� (ko) -> �������: ���� (en)
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // ���� ����
                return readBody(con.getInputStream());
            } else {  // ���� ����
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API ��û�� ���� ����", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL�� �߸��Ǿ����ϴ�. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("������ �����߽��ϴ�. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API ������ �дµ� �����߽��ϴ�.", e);
        }
    }
    
	public static void main(String[] agrs) {
		new TextConverter();
	}
}
