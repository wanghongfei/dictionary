package cn.fh.dictionary.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import cn.fh.dictionary.source.YoudaoParser;
import cn.fh.dictionary.word.Explaination;

public class YoudaoParserTest extends YoudaoParser {
	@Test
	public void testParse() throws Exception {
		setHtml(readAsString("have-youdao.html"));

		List<Explaination> expList = parse();
		
		expList.stream().forEach((exp) -> {
			System.out.println(exp);
		});
	}
	
	private String readAsString(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ( (line = reader.readLine()) != null ) {
			sb.append(line);
		}
		
		reader.close();
		
		return sb.toString();
	}
}
