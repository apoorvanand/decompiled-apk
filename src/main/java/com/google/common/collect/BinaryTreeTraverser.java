package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;
import java.util.Iterator;

@GwtCompatible
@Beta
public abstract class BinaryTreeTraverser<T> extends TreeTraverser<T> {

    private final class InOrderIterator extends AbstractIterator<T> {
        private final BitSet hasExpandedLeft = new BitSet();
        private final Deque<T> stack = new ArrayDeque(8);

        InOrderIterator(T t) {
            this.stack.addLast(t);
        }

        /* access modifiers changed from: protected */
        public T computeNext() {
            while (!this.stack.isEmpty()) {
                T last = this.stack.getLast();
                if (this.hasExpandedLeft.get(this.stack.size() - 1)) {
                    this.stack.removeLast();
                    this.hasExpandedLeft.clear(this.stack.size());
                    BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.rightChild(last));
                    return last;
                }
                this.hasExpandedLeft.set(this.stack.size() - 1);
                BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.leftChild(last));
            }
            return a();
        }
    }

    private final class PostOrderIterator extends UnmodifiableIterator<T> {
        final /* synthetic */ BinaryTreeTraverser a;
        private final BitSet hasExpanded;
        private final Deque<T> stack;

        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        public T next() {
            while (true) {
                T last = this.stack.getLast();
                if (this.hasExpanded.get(this.stack.size() - 1)) {
                    this.stack.removeLast();
                    this.hasExpanded.clear(this.stack.size());
                    return last;
                }
                this.hasExpanded.set(this.stack.size() - 1);
                BinaryTreeTraverser.pushIfPresent(this.stack, this.a.rightChild(last));
                BinaryTreeTraverser.pushIfPresent(this.stack, this.a.leftChild(last));
            }
        }
    }

    private final class PreOrderIterator extends UnmodifiableIterator<T> implements PeekingIterator<T> {
        final /* synthetic */ BinaryTreeTraverser a;
        private final Deque<T> stack;

        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        public T next() {
            T removeLast = this.stack.removeLast();
            BinaryTreeTraverser.pushIfPresent(this.stack, this.a.rightChild(removeLast));
            BinaryTreeTraverser.pushIfPresent(this.stack, this.a.leftChild(removeLast));
            return removeLast;
        }

        public T peek() {
            return this.stack.getLast();
        }
    }

    /* access modifiers changed from: private */
    public static <T> void pushIfPresent(Deque<T> deque, Optional<T> optional) {
        if (optional.isPresent()) {
            deque.addLast(optional.get());
        }
    }

    public final Iterable<T> children(final T t) {
        Preconditions.checkNotNull(t);
        return new FluentIterable<T>() {
            public Iterator<T> iterator() {
                return new AbstractIterator<T>() {
                    boolean a;
                    boolean b;

                    /* access modifiers changed from: protected */
                    public T computeNext() {
                        if (!this.a) {
                            this.a = true;
                            Optional leftChild = BinaryTreeTraverser.this.leftChild(t);
                            if (leftChild.isPresent()) {
                                return leftChild.get();
                            }
                        }
                        if (!this.b) {
                            this.b = true;
                            Optional rightChild = BinaryTreeTraverser.this.rightChild(t);
                            if (rightChild.isPresent()) {
                                return rightChild.get();
                            }
                        }
                        return a();
                    }
                };
            }
        };
    }

    public final FluentIterable<T> inOrderTraversal(final T t) {
        Preconditions.checkNotNull(t);
        return new FluentIterable<T>() {
            public UnmodifiableIterator<T> iterator() {
                return new InOrderIterator(t);
            }
        };
    }

    public abstract Optional<T> leftChild(T t);

    public abstract Optional<T> rightChild(T t);
}
