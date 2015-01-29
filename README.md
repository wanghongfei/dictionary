## Dictionary

### Introduction
* 可以从多家词典网站查询单词，目前已完成对有道词典(dict.youdao.com)和金山词霸(www.iciba.com)的适配.
* 后续计划适配百度翻译
* 提供CLI和GUI用户接口

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

### GUI usage
