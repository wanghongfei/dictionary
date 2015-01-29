## Dictionary

### Introduction
* 可以从多家词典网站查询单词，目前已完成对有道词典(dict.youdao.com)和金山词霸(www.iciba.com)的适配.
* 后续计划适配百度翻译
* 提供CLI和GUI用户接口
* `cn.fh.dictionary.connection.Connector`接口定义了一个连接器，一个连接器需要与`Source`, `Parser` 组合在一起才能使用。该接口的实现类为`DefaultConnector`
* `cn.fh.dictionary.source.Source`接口定义了查询来源，该接口实现类为`YoudaoSource`, `KingsoftSource`
* `cn.fh.dictionary.word`包定义了单词(`Word`),例句(`Sentence`), 释义(`Explaination`)
* `cn.fh.dictionary.source.Parser`接口定义了指定数据源的HTML解析器，实现类有`YoudaoParser`, `KingsoftParser`

### CLI usage
```
dict-cli.sh [option] [parameter]
	option:
		-w: 指定要查询的单词
		-s: 指定词典(暂只支持-s youdao 或 -s kingsoft)
		-h: 显示此信息
	example:
		 dict-cli.sh -w apple // 查apple单词
		 dict-cli.sh -w apple -s youdao // 从有道词典中查apple单词
		 dict-cli.sh -w apple -s kingsoft // 从金山词霸中查apple单词
```
output:
```
单词: condition
	 词性:N-SING
	 英文释意:If you talk about the condition of a person or thing, you are talking about the state that they are in, especially how good or bad their physical state is. (尤指健康) 状况
	 中文释意:null


		 例句:He remains in a critical condition in a California hospital.
		     他在加利福尼亚的一家医院里，仍处于危急的状况。

		 例句:I received several compliments on the condition of my skin.
		     我得到好几次有关我皮肤的状况的夸赞。

		 例句:The two-bedroom chalet is in good condition.
		     这个有两间卧室的小屋状况良好。

	 词性:N-PLURAL
	 英文释意:The conditions under which something is done or happens are all the factors or circumstances which directly affect it. (某事完成或发生的) 条件
	 中文释意:null


		 例句:It's easy to make a wrong turn here even under ideal weather conditions.
		     即使在非常理想的天气条件下，也很容易在这里拐错弯。

	 词性:N-PLURAL
	 英文释意:The conditions in which people live or work are the factors which affect their comfort, safety, or health. (生活或工作的) 环境
	 中文释意:null


		 例句:People are living in appalling conditions.
		     人们生活在极其恶劣的环境中。

		 例句:I could not work in these conditions any longer.
		     我再也不能在这样的环境中工作了。

	 词性:N-COUNT
	 英文释意:A condition is something which must happen or be done in order for something else to be possible, especially when this is written into a contract or law. (尤指写进合同或法律中的某事发生的) 条件
	 中文释意:null


		 例句:Argentina failed to hit the economic targets set as a condition for loan payments.
		     阿根廷没能达到作为贷款偿还条件而设的的经济目标。

		 例句:...terms and conditions of employment.
		     …雇用条款和条件。

	 词性:N-COUNT
	 英文释意:If someone has a particular condition, they have an illness or other medical problem. 疾病; 问题
	 中文释意:null


		 例句:Doctors suspect he may have a heart condition.
		     医生怀疑他可能有心脏病。

	 词性:V-T
	 英文释意:If someone is conditioned by their experiences or environment, they are influenced by them over a period of time so that they do certain things or think in a particular way. 习惯于 (以某种方式做事或思考)
	 中文释意:null


		 例句:We are all conditioned by early impressions and experiences.
		     我们都受早年印象和经历的长期影响。

		 例句:I just feel women are conditioned into doing housework.
		     我只是觉得女人习惯于做家务。

	 词性:N-UNCOUNT
	 英文释意:conditioning 长期影响
	 中文释意:null


		 例句:Because of social conditioning, men don't expect to be managed by women.
		     由于社会的长期影响，男人不希望被���人管。

	 词性:PHRASE
	 英文释意:When you agree to do something on condition that something else happens, you mean that you will only do it if this other thing also happens. 在 (某事发生的) 条件下
	 中文释意:null


		 例句:He agreed to speak to reporters on condition that he was not identified.
		     他同意在不暴露身份的条件下和记者谈话。
```

### GUI usage
