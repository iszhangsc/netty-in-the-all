package io.chat.server;

import io.chat.server.socket.NettyServer;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 8:38 下午
 */
@Slf4j
@SpringBootApplication
public class ChatServerApplication implements CommandLineRunner {

    @Autowired
    private NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(ChatServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("NettyServer 启动服务启动 port: 7379 ");
        Future<Channel> future = Executors.newFixedThreadPool(2).submit(nettyServer);
        Channel channel = future.get();
        if (channel == null) {
            throw new RuntimeException("netty server start error, channel is null");
        }
        while (!channel.isActive()) {
            log.info("NettyServer 启动服务中 .........");
            TimeUnit.MILLISECONDS.sleep(500);
        }
        log.info("NettyServer 启动服务完成 {}", channel.localAddress());
    }


}
