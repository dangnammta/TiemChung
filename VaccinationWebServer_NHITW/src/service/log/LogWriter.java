//package service.log;
//
//import java.util.ArrayList;
//import java.util.List;
//import sns.backend.transport.ScribeLogClient;
//import baseclass.BackgroundExecutor;
//import scribe.thrift.LogEntry;
//
//public class LogWriter {
//
//	private static final LogWriter _instance = new LogWriter();
//
//	public static LogWriter instance() {
//		return _instance;
//	}
//	private ScribeLogClient logInstance = null;
//
//	public LogWriter() {
//		logInstance = ScribeLogClient.instance();
//	}
//
//	public void writeLog(EventWCLogData logData) {
//		String logRequestData = logData.getLogRequestData();
//		String logActionData = logData.getLogActionData();
//
//		List<LogEntry> logEntries = new ArrayList<LogEntry>();
//		if (!logRequestData.isEmpty()) {
//			LogEntry entry = new LogEntry();
//			entry.category = EventWCLogData.CATEGORY_REQUEST;
//			entry.message = logRequestData;
//			logEntries.add(entry);
//		}
//		if (!logActionData.isEmpty()) {
//			LogEntry entry = new LogEntry();
//			entry.category = EventWCLogData.CATEGORY_ACTION;
//			entry.message = logActionData;
//			logEntries.add(entry);
//		}
//
//		if (logInstance != null) {
//			logInstance.Log2(logEntries);
//		}
//	}
//
//	public void writeLogAsync(EventWCLogData logData) {
//		BackgroundExecutor.get().submit(new WriteLogWorker(this, logData));
//	}
//
//	static class WriteLogWorker implements Runnable {
//
//		public WriteLogWorker(LogWriter writer, EventWCLogData data) {
//			_writer = writer;
//			_data = data;
//		}
//
//		@Override
//		public void run() {
//			_writer.writeLog(_data);
//		}
//		private final LogWriter _writer;
//		private final EventWCLogData _data;
//
//	}
//}
