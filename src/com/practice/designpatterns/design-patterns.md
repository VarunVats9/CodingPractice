# BEHAVIORAL PATTERNS

## CHAIN OF RESPONSIBILITY

_recognizeable by behavioral methods which (indirectly) invokes the same method in another implementation of same abstract/interface type in a queue_

> java.util.logging.Logger#log()
> javax.servlet.Filter#doFilter()


## COMMAND

_recognizeable by behavioral methods in an abstract/interface type which invokes a method in an implementation of a different
abstract/interface type which has been encapsulated by the command implementation during its creation_

> All implementations of java.lang.Runnable

[How it looks like ?](https://stackoverflow.com/questions/35610215/java-command-pattern-example-with-runnable-class-is-receiver-missing)


## ITERATOR

_recognizeable by behavioral methods sequentially returning instances of a different type from a queue_

> All implementations of java.util.Iterator

[Why it should not expose the internal representation ?](https://softwareengineering.stackexchange.com/questions/299347/iterator-pattern-why-is-it-important-to-not-expose-the-internal-representation)
* Because, you should not promise, more than you can deliver or your client needs. Client just wants to traverse, and
maybe currently you are using Array, and maybe in future you change it to LinkedList. What if client got to know, and implemented the code based on Array.


## MEDIATOR

_recognizeable by behavioral methods taking an instance of different abstract/interface type (usually using the command pattern) which delegates/uses the given instance_

> java.util.Timer (all scheduleXXX() methods)
> java.util.concurrent.Executor#execute()
> java.util.concurrent.ExecutorService (the invokeXXX() and submit() methods)
> java.util.concurrent.ScheduledExecutorService (all scheduleXXX() methods)


## OBSERVER

_recognizeable by behavioral methods which invokes a method on an instance of another abstract/interface type, depending on own state_

> All implementations of java.util.EventListener
> javax.servlet.http.HttpSessionBindingListener
> javax.servlet.http.HttpSessionAttributeListener

[Complicated objects situation](https://softwareengineering.stackexchange.com/questions/317164/observer-design-pattern-with-complicated-objects)