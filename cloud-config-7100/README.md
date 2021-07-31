> 注意点

1、不能直接采用ip访问，配置中心将127.0.0.1映射为cloud-config域名(经过验证不映射域名也可以)

 2、SpringCloudConfig采用的密钥加载方式，是以*-----BEGIN RSA PRIVATE KEY-----开头和以-----END RSA PRIVATE KEY-----结尾的
 ，而不是以-----BEGIN OPENSSH PRIVATE KEY-----开头和以-----END OPENSSH PRIVATE KEY-----**


 所以要用下面的命令:

 ssh-keygen -m PEM -t rsa -b 4096 -C "842089160@qq.com" -f ~/.ssh/gitee_id_rsa

否则会报密钥加载失败，这次可以参考这里可以参考这篇文章：

https://blog.csdn.net/qq_36209121/article/details/96832689

3、Git上的配置文件必须为UTF-8格式的，如遇乱码参考

https://blog.csdn.net/weixin_42941199/article/details/111288878