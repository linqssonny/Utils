#使用方式
    compile 'com.sonnyjack.utils:Utils:0.1.0'  或者
    implementation 'com.sonnyjack.utils:Utils:0.1.0'

如果你的项目混淆：

-dontwarn com.sonnyjack.utils.**

-keep class com.sonnyjack.utils.** {*;}

下面简单介绍一下该工具库里的api。

com.sonnyjack.utils.app：

	AppUtils：用来记录打开的Activity，包含获取栈顶的Activity、关闭指定Activity等等。
com.sonnyjack.utils.bitmap：

	BitmapUtils：decode指定大小的bitmap、保存bitmap到指定路径、生成微信分享缩略图、裁剪指定大小的bitmap等等。
com.sonnyjack.utils.collection：

	CollectionUtils： 提供判断集合(set、list、map)、数组是否为空。
com.sonnyjack.utils.date：

	DateUtils：format指定时期格式、判断是否同一天等。
com.sonnyjack.utils.density：

	DensityUtils：封装dp、px、sp互相转换。
com.sonnyjack.utils.file：

	FileUtils：复制文件、保存字符串到指定文件、读取指定文件、删除文件或文件夹、根据url获取文件名字等。
com.sonnyjack.utils.json：

	JsonUtils：含有数组生成json格式字符串、Map生成json格式字符串、json格式字符串生成转为Map、根据key获取json字符串的value值。
com.sonnyjack.utils.log：

	LogUtils：日志的打印方法的封装，不是很完善。
com.sonnyjack.utils.net：

	NetUtils：网络是否链接判断和是否是wifi链接判断。
com.sonnyjack.utils.screen：

	ScreenUtils：获取屏幕宽高。
com.sonnyjack.utils.security：

	AESUtils：提供aes加解密方法，可自行转递key。
	SecurityUtils：内有MD5、SHA256、SHA512加密方法。
com.sonnyjack.utils.sp：

	SPUtils：封装了SharedPreferences的相关方法，用户需在Application的onCreate调用SPUitls.getInstance().init("Context上下文"，"SP的文件名，可不传，默认为包名");
com.sonnyjack.utils.stream：

	StreamUtils：实现了Stream的close方法，使代码更加简洁，省去try...catch...，InputStream转为byte数组等。
com.sonnyjack.utils.system：

	SystemUtils：Uri转为Absolute路径、获取SD卡根目录。
com.sonnyjack.utils.toast：

	ToastUtils：封装了Toast的弹出操作，简化代码。
com.sonnyjack.utils.version：

	VersionUtils：提供获取版本号(VersionCode)、版本名字(VersionName)。


如果遇到什么问题可以加我Q：252624617  或者issues反馈