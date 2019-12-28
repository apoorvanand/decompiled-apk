package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;

@GwtCompatible
@Beta
public abstract class TreeTraverser<T> {

    private final class BreadthFirstIterator extends UnmodifiableIterator<T> implements PeekingIterator<T> {
        private final Queue<T> queue = new ArrayDeque();

        BreadthFirstIterator(T t) {
            this.queue.add(t);
        }

        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        public T next() {
            T remove = this.queue.remove();
            Iterables.addAll(this.queue, TreeTraverser.this.children(remove));
            return remove;
        }

        public T peek() {
            return this.queue.element();
        }
    }

    private final class PostOrderIterator extends AbstractIterator<T> {
        private final ArrayDeque<PostOrderNode<T>> stack = new ArrayDeque<>();

        PostOrderIterator(T t) {
            this.stack.addLast(expand(t));
        }

        private PostOrderNode<T> expand(T t) {
            return new PostOrderNode<>(t, TreeTraverser.this.children(t).iterator());
        }

        /* access modifiers changed from: protected */
        public T computeNext() {
            while (!this.stack.isEmpty()) {
                PostOrderNode last = this.stack.getLast();
                if (last.b.hasNext()) {
                    this.stack.addLast(expand(last.b.next()));
                } else {
                    this.stack.removeLast();
                    return last.a;
                }
            }
            return a();
        }
    }

    private static final class PostOrderNode<T> {
        final T a;
        final Iterator<T> b;

        PostOrderNode(T t, Iterator<T> it) {
            this.a = Preconditions.checkNotNull(t);
            this.b = (Iterator) Preconditions.checkNotNull(it);
        }
    }

    private final class PreOrderIterator extends UnmodifiableIterator<T> {
        private final Deque<Iterator<T>> stack = new ArrayDeque();

        PreOrderIterator(T t) {
            this.stack.addLast(Iterators.singletonIterator(Preconditions.checkNotNull(t)));
        }

        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        public T next() {
            Iterator last = this.stack.getLast();
            T checkNotNull = Preconditions.checkNotNull(last.next());
            if (!last.hasNext()) {
                this.stack.removeLast();
            }
            Iterator it = TreeTraverser.this.children(checkNotNull).iterator();
            if (it.hasNext()) {
                this.stack.addLast(it);
            }
            return checkNotNull;
        }
    }

    public static <T> TreeTraverser<T> using(final Function<T, ? extends Iterable<T>> function) {
        Preconditions.checkNotNull(function);
        return new TreeTraverser<T>() {
            public Iterable<T> children(T t) {
                return (Iterable) function.apply(t);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public UnmodifiableIterator<T> a(T t) {
        return new PreOrderIterator(t);
    }

    /* access modifiers changed from: package-private */
    public UnmodifiableIterator<T> b(T t) {
        return new PostOrderIterator(t);
    }

    public final FluentIterable<T> breadthFirstTraversal(final T t) {
        Preconditions.checkNotNull(t);
        return new FluentIterable<T>() {
            public UnmodifiableIterator<T> iterator() {
                return new BreadthFirstIterator(t);
            }
        };
    }

    public abstract Iterable<T> children(T t);

    public final FluentIterable<T> postOrderTraversal(final T t) {
        Preconditions.checkNotNull(t);
        return new FluentIterable<T>() {
            public UnmodifiableIterator<T> iterator() {
                return TreeTraverser.this.b(t);
            }
        };
    }

    public final FluentIterable<T> preOrderTraversal(final T t) {
        Preconditions.checkNotNull(t);
        return new FluentIterable<T>() {
            public UnmodifiableIterator<T> iterator() {
                return TreeTraverser.this.a(t);
            }
        };
    }
}
