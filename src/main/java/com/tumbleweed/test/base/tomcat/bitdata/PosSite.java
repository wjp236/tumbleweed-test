package com.tumbleweed.test.base.tomcat.bitdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 描述:收银模拟系统
 *
 * @author: mylover
 * @Time: 07/12/2017.
 */
public class PosSite {

    private Thread thread;
    private long posNumber;
    private String shopName;//执行线程
    private volatile long count;//执行总数
    private volatile long success;
    private volatile long fails;

    private static final TradeProduct[] mockProducts;


    private ExecutorService pool;

    {
        pool = new ThreadPoolExecutor(200,200,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(1000));
    }

    static {
        mockProducts = new TradeProduct[10];
        mockProducts[0] = new TradeProduct(0, "0", "0", 0);
        mockProducts[1] = new TradeProduct(1, "1", "1", 1);
        mockProducts[2] = new TradeProduct(0, "0", "0", 0);
        mockProducts[3] = new TradeProduct(0, "0", "0", 0);
        mockProducts[4] = new TradeProduct(0, "0", "0", 0);
        mockProducts[5] = new TradeProduct(0, "0", "0", 0);
        mockProducts[6] = new TradeProduct(0, "0", "0", 0);
        mockProducts[7] = new TradeProduct(0, "0", "0", 0);
        mockProducts[8] = new TradeProduct(0, "0", "0", 0);
        mockProducts[9] = new TradeProduct(0, "0", "0", 0);

    }

    public PosSite(int posNumber, String name) {
        this.posNumber = posNumber;
        this.shopName = name;
        pool = Executors.newCachedThreadPool();

    }

    public static void main(String[] args) {

        List<PosSite> sites = new ArrayList<>();
        int initStartCount = Integer.parseInt(args.length > 0 ? args[0] : "20");
        for (int i = 0; i < initStartCount; i++) {
            PosSite p = new PosSite(i, "沃尔玛解放路店");
            sites.add(p);
            p.start();
        }

        long  count = 0, success = 0, fails = 0;

        while (true) {
            long newCount = 0, newSuccess = 0, newFails = 0;
            for (PosSite p: sites) {
                newCount += p.getCount();
                newSuccess += p.getSuccess();
                newFails += p.getFails();
            }

            long tps = (newCount - count) / 2;

            count = newCount;
            success = newSuccess;
            fails = newFails;

            System.out.println(tps +","+ count +","+ success +","+ fails);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void start() {

        stop();

        thread = new Thread() {
            private volatile boolean isRun = true;

            @Override
            public void run() {
                while (isRun) {
                    pool.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                executeCashier();
                                success++;
                            } catch (Exception e) {
                                e.printStackTrace();
                                fails++;
                            } finally {
                                count++;
                            }
                        }
                    });

                    try {
                        Thread.sleep(new Random().nextInt(200));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void destroy() {
                isRun = false;
            }

        };
        thread.start();
    }

    private String executeCashier() throws Exception {

        String data = JSON.toJSONString(mockTradeDate(), SerializerFeature.DisableCircularReferenceDetect);

        data = URLEncoder.encode(data, "utf-8");

        URL url = new URL("http://localhost:8080/data");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setReadTimeout(5000);
        conn.setConnectTimeout(5000);
        OutputStream out = conn.getOutputStream();
        out.write(("data=" + data).getBytes());

        Thread.sleep(2000);

        out.flush();
        out.close();

        InputStream input = conn.getInputStream();

        byte[] b = sun.misc.IOUtils.readFully(input, -1, true);

        input.close();
        conn.disconnect();

        return new String(b);

    }

    private Object mockTradeDate() {
        PosTradeRecord node = new PosTradeRecord();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmssSSSS");
        int s = new Random().nextInt(10000);
        node.setOrderId(f.format(new Date()) + s);
        node.setOrderMoney(Long.valueOf(new Random().nextInt(1000000)));
        node.setPosNumber(posNumber);
        node.setShopName(shopName);
        node.setTradeDate(System.currentTimeMillis());

        List<TradeProduct> products = new ArrayList<>();
        int pSize = new Random().nextInt(20) + 1;
        for (int i = 0; i < pSize; i++) {
            products.add(mockProducts[new Random().nextInt(10)]);
        }
        node.setProducts(products);
        return node;
    }

    public void stop() {
        if (thread != null && thread.isAlive()) {
            thread.destroy();
        }
    }


    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public long getPosNumber() {
        return posNumber;
    }

    public void setPosNumber(long posNumber) {
        this.posNumber = posNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getSuccess() {
        return success;
    }

    public void setSuccess(long success) {
        this.success = success;
    }

    public long getFails() {
        return fails;
    }

    public void setFails(long fails) {
        this.fails = fails;
    }

    public static TradeProduct[] getMockProducts() {
        return mockProducts;
    }

    public ExecutorService getPool() {
        return pool;
    }

    public void setPool(ExecutorService pool) {
        this.pool = pool;
    }
}
