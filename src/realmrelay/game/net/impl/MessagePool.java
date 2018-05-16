 
package realmrelay.game.net.impl;

	public class MessagePool {

        public Class type;

        public Function callback;

        public int id;

        private Message tail;

        private int count = 0;

        public MessagePool(int param1, Class param2, Function param3) {
            this.type = param2;
            this.id = param1;
            this.callback = param3;
        }

        public MessagePool populate(int param1) {
            Message loc2 = null;
            this.count = this.count + param1;
            while (param1--) {
                loc2 = new this.type(this.id, this.callback);
                loc2.pool = this;
                this.tail && (this.tail.next = loc2);
                loc2.prev = this.tail;
                this.tail = loc2;
            }
            return this;
        }

        public Message require() {
            Message loc1 = this.tail;
            if (loc1 != null) {
                this.tail = this.tail.prev;
                loc1.prev = null;
                loc1.next = null;
            } else {
                loc1 = new this.type(this.id, this.callback);
                loc1.pool = this;
                this.count++;
            }
            return loc1;
        }

        public int getCount() {
            return this.count;
        }

        void append(Message param1) {
            this.tail && (this.tail.next = param1);
            param1.prev = this.tail;
            this.tail = param1;
        }

        public void dispose() {
            this.tail = null;
        }
	}

