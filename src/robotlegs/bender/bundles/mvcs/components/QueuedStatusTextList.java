package robotlegs.bender.bundles.mvcs.components;

import flash.display.DisplayObjectContainer;

public class QueuedStatusTextList {

	public DisplayObjectContainer target;

	private QueuedStatusText head;

	private QueuedStatusText tail;

	public QueuedStatusTextList() {
		super();
	}

	public void shift() {
		this.target.removeChild(this.head);
		this.head = this.head.next;
		if (this.head != null) {
			this.target.addChild(this.head);
		} else {
			this.tail = null;
		}
	}

	public void append(QueuedStatusText param1) {
		param1.list = this;
		if (this.tail != null) {
			this.tail.next = param1;
			this.tail = param1;
		} else {
			this.head = this.tail = param1;
			this.target.addChild(param1);
		}
	}


}
