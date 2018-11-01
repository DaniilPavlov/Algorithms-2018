package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        if (root == null) {
            return false;
        }
        Node<T> parent = root;
        Node<T> curr = root;
        int cmp;
        int currIsLeft = 0;
        while ((cmp = curr.value.compareTo((T) o)) != 0) {
            parent = curr;
            if (cmp > 0) {
                currIsLeft = 1;
                curr = curr.left;
            } else {
                currIsLeft = -1;
                curr = curr.right;
            }
            if (curr == null) {
                return false;
            }
        }
        if (curr.left != null && curr.right != null) {
            Node<T> next = curr.right;
            Node<T> pNext = curr;
            while (next.left != null) {
                pNext = next;
                next = next.left;
            }
            if (root == curr) {
                root = next;
            }
            if (pNext == curr) {
                curr.right = next.right;
            } else {
                pNext.left = next.right;
            }
            next.left = curr.left;
            next.right = curr.right;
            switch (currIsLeft) {
                case 1:
                    parent.left = next;
                    break;
                case -1:
                    parent.right = next;
                    break;
            }
        } else {
            if (curr.left != null) {
                reLink(parent, curr, curr.left);
            } else if (curr.right != null) {
                reLink(parent, curr, curr.right);
            } else {
                reLink(parent, curr, null);
            }
        }
        size--;
        return true;
    }

    private void reLink(Node<T> parent, Node<T> curr, Node<T> child) {
        if (parent == curr) {
            root = child;
        } else if (parent.left == curr) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        curr = new Node<>(null);
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private Stack<Node<T>> stack;

        private BinaryTreeIterator() {
            stack = new Stack<>();
            addToStack(root);
        }

        private void addToStack(Node<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        private Node<T> findNext() {
            return stack.pop();
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            current = findNext();
            if (current.right != null) {
                addToStack(current.right);
            }
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            BinaryTree.this.remove(current.value);
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */

    // Трудоемкость: T = O(n)
    // Ресурсоемкость: R = O(n)
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        SortedSet<T> set = new TreeSet<>();
        headSetAlgorithm(toElement, set, root);
        return set;
    }

    public void headSetAlgorithm(T toElement, SortedSet<T> set, Node<T> currentNode) {
        int compareOfBranches = currentNode.value.compareTo(toElement);
        switch (compareOfBranches) {
            case -1:
                set.add(currentNode.value);
                if (currentNode.left != null) additionOfBranchToSet(set, currentNode.left);
                if (currentNode.right != null) headSetAlgorithm(toElement, set, currentNode.right);
                break;
            case 0:
                if (currentNode.left != null) additionOfBranchToSet(set, currentNode.left);
                break;
            case 1:
                if (currentNode.left != null) headSetAlgorithm(toElement, set, currentNode.left);
                break;
        }
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */

    // Трудоемкость: T = O(n)
    // Ресурсоемкость: R = O(n)
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        SortedSet<T> set = new TreeSet<>();
        tailSetAlgorithm(fromElement, set, root);
        return set;
    }

    public void additionOfBranchToSet(SortedSet<T> set, Node<T> currentNode) {
        set.add(currentNode.value);
        if (currentNode.left != null) additionOfBranchToSet(set, currentNode.left);
        if (currentNode.right != null) additionOfBranchToSet(set, currentNode.right);
    }

    public void tailSetAlgorithm(T toElement, SortedSet<T> set, Node<T> currentNode) {
        int compareOfBranches = currentNode.value.compareTo(toElement);
        switch (compareOfBranches) {
            case -1:
                if (currentNode.right != null) tailSetAlgorithm(toElement, set, currentNode.right);
                break;
            case 0:
            case 1:
                set.add(currentNode.value);
                if (currentNode.left != null) tailSetAlgorithm(toElement, set, currentNode.left);
                if (currentNode.right != null) additionOfBranchToSet(set, currentNode.right);
                break;
        }
    }


    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
