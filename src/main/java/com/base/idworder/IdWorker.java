/**
 * CopyRight 2014 必拓电子商务有限公司
 */
package com.base.idworder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ID生成工人
 * @version 2015年7月13日 下午2:12:40
 * @author John<wangcyg@enn.cn>
 */
public class IdWorker {
	private static final Logger logger = LoggerFactory.getLogger(IdWorker.class);
	private long workerId;
	private long datacenterId;
	private long sequence = 0L;
	/** 2015-07-01 00:00:00 000 */
	private long twepoch = 1465660800L;

	private long workerIdBits = 5L;
	private long datacenterIdBits = 5L;
	private long maxWorkerId = -1L ^ (-1L << workerIdBits);
	private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private long sequenceBits = 10L;

	private long workerIdShift = sequenceBits;
	private long datacenterIdShift = sequenceBits + workerIdBits;
	private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	private long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long lastTimestamp = -1L;

	/**
	 * 构造方法
	 * @param workerId 系统编号
	 * @param datacenterId 中心编号
	 */
	public IdWorker(long workerId, long datacenterId) {
		// sanity check for workerId
		if (workerId > maxWorkerId || workerId < 0) {
			String errorMsg = String.format("worker Id[%d] can't be greater than %d or less than 0", workerId, maxWorkerId);
			logger.error(errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		// sanity check for datacenterId
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			String errorMsg = String.format("datacenter Id[%d] can't be greater than %d or less than 0", datacenterId, maxDatacenterId);
			logger.error(errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
		String info = String.format("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
				timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);
		logger.info(info);
	}

	public synchronized long nextId() {
		long timestamp = timeGen();

		if (timestamp < lastTimestamp) {
			String errorMsg = String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp);
			logger.error(errorMsg);
			throw new RuntimeException(errorMsg);
		}

		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0L;
		}

		lastTimestamp = timestamp;
		return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
	}

	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	protected long timeGen() {
		return System.currentTimeMillis()/1000;
	}


}
