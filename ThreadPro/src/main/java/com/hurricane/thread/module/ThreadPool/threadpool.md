
所有的线程池  都是ThreadPoolExecutor 的封装

1.有界队列 要提供最大线程数
ArrayBlockQueue 固定长度的任务队列。当空闲线程数量达到coreThreadCount 数量时，会将多余的任务暂时放入有界任务队列。如果长时间处理速度小于任务的生成速度，任务队列无法再容纳任务时，系统会重新创建线程，当线程数量达到maxThreadCount的时候，执行拒绝策略
2.无界队列-- fixThreadPool，singleThreadPool
LinkBlockQueue  由于线程数量固定，所以当空闲线程不能及时处理任务时 会添加到任务队列中，如果长时间处理速度小于任务的生成速度，任务队列会无限制的添加任务，知道系统资源用尽。
3.优先级队列
4.直接提交队列  --cacheThreadPool 一般要提供最大线程数
SynchronousQueue  该任务队列不保存任务，每一个删除操作对应都有一个插入的操作，他总是将任务直接提交给线程，当没有空闲线程的时候会创建线程，当达到maxThreadCount的时候，执行决绝策略
