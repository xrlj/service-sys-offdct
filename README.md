# service-sys-offdct
excel、word、pdf等办公文档处理服务。只提供抽象的处理方法，与业务无关。

## docker运行服务

    docker run --name service-sys-genid -v /server/dockers/service-sys-offdct/app:/app -v /server/dockers/service-sys-offdct/tmp:/tmp -v /server/dockers/service-sys-offdct/logs:/logs -v /server/dockers/service-sys-offdct/tmp:~/.xrlj/service-sys-offdct -p 9030:9030 -d 120.79.2.30:8082/xrlj/service-sys-genid:0.0.1
