![image](https://raw.githubusercontent.com/wangtianbao1987/ttsClientDemo/master/pachira.png)
## 普强TTS接口调用演示实例说明
### 运行入口
* com.pachira.tts.client.Main
### 参数配置类
* com.pachira.tts.client.ReqParam
### 使用说明

系统为客户端调用普强TTS服务接口演示实例。

* com.pachira.tts.client.Main

提供如下信息的配置：

    请求方式：GET/POST
    播放/下载：play/download
    请求HTTP时的编程方式：socket/httpClient工具类
    
程序如下：

```

// HTTP请求方式：POST/GET
String method = "POST";
// 测试使用。 play:播放；    download:下载
String playOrDownload = "play";
//测试使用。httpClient:使用httpClient框架的方式调用TTS接口； socket:使用socket拼报文的形式调用TTS接口。 
//若选择socket方式，程序将打印HTTP报文
// 1.2.1版本后，使用socket可以将合成过程中出现的错误信息打印出来；使用httpClient暂不支持打印错误信息。
String demoType = "socket";

```
* com.pachira.tts.client.ReqParam

提供如下信息的配置，请自行进入文件中查看：

    请求HTTP的路径【url】、下载/临时文件存放位置【downloadDir】、传递参数

* 具体实现过程请自行debug运行查看

### 请求报文说明：
#### 在1.2.0版本之前支持的报文如下：
* GET提交
```
GET /voice/tts?text=Hello%2C%E4%BD%A0%E5%A5%BD&volume=1&speed=1&pitch=1&sample_rate=16000&tag_mode=1&eng_mode=0&format=pcm&voice_name=xiaochang&language=zh-cmn HTTP/1.1
Host: 172.22.144.138:88
Connection: Keep-Alive
Content-Type: application/x-www-form-urlencoded
```
* POST提交
```
POST /voice/tts HTTP/1.1
Host: 172.22.144.138:88
Connection: Keep-Alive
Content-Type: application/x-www-form-urlencoded
Content-Length: 144

text=Hello%2C%E4%BD%A0%E5%A5%BD&volume=1&speed=1&pitch=1&sample_rate=16000&tag_mode=1&eng_mode=0&format=pcm&voice_name=xiaochang&language=zh-cmn
```
#### 在1.2.1版本之后POST提交增加对JSON字符串的支持，同时请求头可以去掉Content-Type属性：
* GET提交
```
GET /voice/tts?text=Hello%2C%E4%BD%A0%E5%A5%BD&volume=1&speed=1&pitch=1&sample_rate=16000&tag_mode=1&eng_mode=0&format=pcm&voice_name=xiaochang&language=zh-cmn HTTP/1.1
Host: 172.22.144.138:88
Connection: Keep-Alive
```
* POST提交
```
POST /voice/tts HTTP/1.1
Host: 172.22.144.138:88
Connection: Keep-Alive
Content-Length: 144

text=Hello%2C%E4%BD%A0%E5%A5%BD&volume=1&speed=1&pitch=1&sample_rate=16000&tag_mode=1&eng_mode=0&format=pcm&voice_name=xiaochang&language=zh-cmn
```
或者
```
POST /voice/tts HTTP/1.1
Host: 172.22.144.138:88
Connection: Keep-Alive
Content-Length: 172

{"text":"Hello,你好","volume":"1","speed":"1","pitch":"1","sample_rate":"16000","tag_mode":"1","eng_mode":"0","format":"pcm","voice_name":"xiaochang","language":"zh-cmn"}
```

### 响应报文说明：
#### 正常的响应报文如下：
```
HTTP/1.1 200
Content-Disposition: attachment;fileName=tts.pcm
Content-Type: audio/pcm;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 28 Nov 2018 13:33:55 GMT

2000
<[[长度为8192的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
1420
<[[长度为5152的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
680
<[[长度为1664的二进制数据]]>
0
```

#### 系统校验不通过时的响应报文如下：
* 1.2.0版本之前
```
HTTP/1.1 200
Content-Type: text/html;charset=UTF-8
Content-Length: 72
Date: Wed, 28 Nov 2018 13:38:58 GMT

{"err_msg":"语速:参数[speed]取值范围为0.5~2.0","err_no":"7401"}
```

* 1.2.1版本以后，Content-Type修改为application/json;charset=UTF-8
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Content-Length: 72
Date: Wed, 28 Nov 2018 13:38:58 GMT

{"err_msg":"语速:参数[speed]取值范围为0.5~2.0","err_no":"7401"}
```
另：
* 1.2.1版本之后，请求pcm语音时，增加TTS在运行过程中的错误返回，报错信息在最后一个buff中。对应的响应报文如下：
```
HTTP/1.1 200
Content-Disposition: attachment;fileName=tts.pcm
Content-Type: audio/pcm;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 28 Nov 2018 13:33:55 GMT

2000
<[[长度为8192的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
1420
<[[长度为5152的二进制数据]]>
2000
<[[长度为8192的二进制数据]]>
680
<[[长度为1664的二进制数据]]>
40
{"err_msg":"TTS合成语音过大;退出合成","err_no":"7412"}
0
```

* 调用过程中遇到问题可联系我：
```
QQ：    836369864
微信：  wang_tianbao1987
```

