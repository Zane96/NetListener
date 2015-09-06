
开始改用html呈现数据


一个简单的app，可以用来使一个手机通过访问被放在被监听者手机建立的服务器上的网站来监听信息。

目前只做到监听网络是否可用和电量信息。

监听者需要连上被监听者打开的网络热点，然后在被监听者手机上收集数据，生成xml代码，并放在由nanohtppd框架生成的服务器上。

监听者通过访问被监听者的ip地址+端口来获取信息。

如何获取手机IP地址：

192.168还有172这些开头的IPv4地址这些是没有分配的，是可以用来建立内部网络比如局域网，校园网。如果我自己的手机是连入的家里路由器的wifi，那么我手机的IP地址就是由路由器跟我分配的，比如我现在看的是192.168.0.104，所以我可以推论我家里路由器的IP地址就是192.168.0.1.推论一下，路由器的IP地址都是192.168.xx.1，然后连入这个路由器的设备被分配的IP都是192.168.xx.xxx。这里xxx的范围是1到255.比如我现在的手机是连入了家里的路由器，我想去访问百度的页面，也就是外部网络，发送请求之后，路由器会把192.168.xx.xxx这个地址转化为一个单个的外部地址去访问外面的，这叫出站。如果是访问192.168.xx.1，那就直接访问我的路由器了，叫入站。
所以先用被监听者开热点让监听者连上，查看手机ip地址，这个地址就是被监听者分配给你的，吧最后一位改成1，然后打开浏览器输入xxx.xxx.xx.1:8080就可以获取信息了



A simple app to make a cell phone access through IP addresses to listen for other phones by android(Zane.)

I'm a new Android coder. This project is used to make one phone to listener for other phone which connection on its WiFi hotspot.

One phone can get the information like if network is available and battery remaining capacity by browser in your phone. You are supposed to access the phone by its IP address add port. Like this: 192.168.41.1:8080.

This project have used three library.butterkinef, jdom, nanohttpd.

I just used the jdom to build XML file in the phone,but I think it will be more accessible to build HTML file.Because it will make it more helpful to display data in website.Maybe I will use the renderSnake library.

I used nanohttpd to make mobile terminal to be a web servers.

So, this is just a new project and there is a lot of room for improvement.
