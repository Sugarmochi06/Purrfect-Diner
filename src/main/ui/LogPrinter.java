package ui;

import model.EventLog;

/**
 * Defines behaviours that event log printers must support.
 * ** taken from alarm system from CPSC 210
 */
public interface LogPrinter {
	/**
	 * Prints the log
	 * @param el  the event log to be printed
	 * @throws LogException when printing fails for any reason
	 */
    public void printLog(EventLog el);
}
