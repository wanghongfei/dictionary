package cn.fh.dictionary.cli;

import static java.lang.System.out;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fh.dictionary.connection.Connector;
import cn.fh.dictionary.connection.DefaultConnector;
import cn.fh.dictionary.source.KingsoftParser;
import cn.fh.dictionary.source.KingsoftSource;
import cn.fh.dictionary.source.Parser;
import cn.fh.dictionary.source.Source;
import cn.fh.dictionary.source.YoudaoParser;
import cn.fh.dictionary.source.YoudaoSource;
import cn.fh.dictionary.word.Explaination;
import cn.fh.dictionary.word.Word;

/**
 * 通过命令行使用词典
 * @author whf
 *
 */
public class CLI {
	public static void main(String[] args) {
		if (0 == args.length) {
			out.println("请使用-w选项指定要查询的单词");
			showHelp();
			return;
		}

		if (args.length == 1) {
			if (args[0].equals("-h")) {
				showHelp();
				return;
			}
		}
		
		Arguments arg = new Arguments(args);

		Parser parser = null;
		Source source = null;
		Connector connector = new DefaultConnector();

		String dict = arg.getArg("-s");
		if ("youdao".equals(dict)) {
			parser = new YoudaoParser();
			source = new YoudaoSource();
		} else if ("kingsoft".equals(dict)) {
			parser = new KingsoftParser();
			source = new KingsoftSource();
		}

		String word = arg.getArg("-w");
		if (null == word) {
			out.println("-w选项无效");
			System.exit(0);
		}
		
		connector.setSource(source);
		connector.setParser(parser);
		connector.setWord(word);
		Word wordResult = connector.fetchResult();
		
		out.println("单词: " + wordResult.getWord());
		List<Explaination> expList = wordResult.getExplainList();
		expList.stream().forEach((exp) -> {
			out.println("\t 词性:" + exp.getAttribute());
			out.println("\t 英文释义:" + exp.getEngExplaination());
			out.println("\t 中文释义:" + exp.getChinExplaination());
			out.println();
			out.println();
			
			if (null != exp.getSentenceList()) {
				exp.getSentenceList().stream().forEach((sentence) -> {
					out.println("\t\t 例句:" + sentence.getEnglish());
					out.println("\t\t     " + sentence.getChinese());
					out.println();
				});
			}
		});

	}
	
	private static void showHelp() {
		out.println("dict-cli.sh [option] [parameter]");
		out.println("\toption:");
		out.println("\t\t-w: 指定要查询的单词");
		out.println("\t\t-s: 指定词典(暂只支持-s youdao 或 -s kingsoft)");
		out.println("\t\t-h: 显示此信息");
		out.println("\texample:");
		out.println("\t\t dict-cli.sh -w apple // 查apple单词");
		out.println("\t\t dict-cli.sh -w apple -s youdao // 从有道词典中查apple单词");
	}
}


class Arguments {
	private Map<String, String> argMap;
	
	public Arguments(String[] args) {
		argMap = new HashMap<>();
		
		int len = args.length;
		for (int ix = 0 ; ix < len ; ix += 2) {
			if (ix + 1 < len) {
				boolean valid = setArg(args[ix], args[ix + 1]);
				if (!valid) {
					out.println("invalid option:" + args[ix]);
					System.exit(0);
				}
			} else {
				out.println("option '" + args[ix] + "' lacks of argument");
				System.exit(0);
			}
			
		}
	}
	
	public String getArg(String option) {
		return argMap.get(option);
	}

	private boolean setArg(String option, String value) {
		if (2 == option.length()) {
			if (option.startsWith("-")) {
				argMap.put(option, value);
				return true;
			}
		}
		
		return false;
	}
}