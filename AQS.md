``` java
/**
   * Release action for shared mode -- signals successor and ensures
   * propagation. (Note: For exclusive mode, release just amounts
   * to calling unparkSuccessor of head if it needs signal.)
   */
  private void doReleaseShared() {
      /*
       * Ensure that a release propagates, even if there are other
       * in-progress acquires/releases.  This proceeds in the usual
       * way of trying to unparkSuccessor of head if it needs
       * signal. But if it does not, status is set to PROPAGATE to
       * ensure that upon release, propagation continues.
       * Additionally, we must loop in case a new node is added
       * while we are doing this. Also, unlike other uses of
       * unparkSuccessor, we need to know if CAS to reset status
       * fails, if so rechecking.
       */
      for (;;) {
          Node h = head;
          if (h != null && h != tail) {
              int ws = h.waitStatus;
              if (ws == Node.SIGNAL) {      // 1
                  if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                      continue;            // loop to recheck cases
                  unparkSuccessor(h);
              }
              else if (ws == 0 &&           // 2
                       !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                  continue;                // loop on failed CAS
          }
          if (h == head)                   // loop if head changed
              break;
      }
  }
```
// 这里需要注意的是：下一个节点的状态是Node.SIGNAL，节点代表的线程才**可能**被阻塞了，需要unpark

// 1.下一个节点可能被阻塞了，unpark下一个节点，由于在unparkSuccessor方法，会判断当前节点的状态是否小于0，如果小于0则将h的状态设置为0，如果在这里直接设置为PROPAGATE状态的话，则相当于多做了一次CAS操作。unparkSuccessor中的代码如下：
``` java
int ws = node.waitStatus;
if (ws < 0)
    compareAndSetWaitStatus(node, ws, 0);
```
// 2.下一个节点关联的线程还未阻塞过，再给一次机会竞争资源

// 3.`PROPAGATE`不需要做任何操作，后续线程轮询竞争资源
