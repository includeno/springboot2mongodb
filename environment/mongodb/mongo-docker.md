# MongoDB环境配置



## 镜像

```dockerfile
docker pull mongo:4.4.17
```



## 运行

```
docker run -itd -p 27017:27017 --name mongo-container \
	-e MONGODB_INITDB_ROOT_USERNAME=root \
	-e MONGODB_INITDB_ROOT_PASSWORD=root123456 \
	mongo:4.4.17
```



## 日志查看

```
docker logs mongo-container

docker exec -it mongo-container mongo
```



## 销毁容器

```
docker rm mongo-container
```

