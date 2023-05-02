package CORE;

public class MovieBinarySearchTree {
    public Movie root;

    public MovieBinarySearchTree() {
        root = null;
    }

    public boolean isEmptyTree() {
        return root == null;
    }

    public Movie search(int releaseDate) {
        return searchr(root, releaseDate);
    }

    private Movie searchr(Movie node, int releaseDate) {
        if (node == null) {
            return null;
        } else if (releaseDate == node.getReleaseDate()) {
            return node;
        } else if (releaseDate < node.getReleaseDate()) {
            return searchr(node.getLeft(), releaseDate);
        } else {
            return searchr(node.getRight(), releaseDate);
        }
    }

    public void insert(Movie movie) {
        Movie temp = root;
        Movie parent = null;

        while (temp != null) {
            parent = temp;
            if (movie.getReleaseDate() < temp.getReleaseDate()) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }

        if (parent == null) {
            root = movie;
        } else if (movie.getReleaseDate() < parent.getReleaseDate()) {
            parent.setLeft(movie);
        } else {
            parent.setRight(movie);
        }
    }

    public void printTree() {
        printTree2(root);
        System.out.println();
    }

    private void printTree2(Movie movie) {
        if (movie != null) {
            System.out.print(movie.getTitle() + " (" + movie.getReleaseDate() + ") ");
            if (movie.getLeft() != null) {
                System.out.print("Left: " + movie.getLeft().getTitle() + " ");
            } else {
                System.out.print("Left: null ");
            }
            if (movie.getRight() != null) {
                System.out.println("Right: " + movie.getRight().getTitle() + " ");
            } else {
                System.out.println("Right: null ");
            }
            printTree2(movie.getLeft());
            printTree2(movie.getRight());
        }
    }

    public void delete(Movie movie) {
        int releaseDate = movie.getReleaseDate();
        root = deleter(root, releaseDate);
    }

    private Movie deleter(Movie node, int releaseDate) {
        if (node == null) {
            return null;
        }

        if (releaseDate < node.getReleaseDate()) {
            node.setLeft(deleter(node.getLeft(), releaseDate));
        } else if (releaseDate > node.getReleaseDate()) {
            node.setRight(deleter(node.getRight(), releaseDate));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                Movie temp = node;
                node = minReleaseDate(node.getRight());
                temp.setRight(deleter(temp.getRight(), node.getReleaseDate()));
                node.setRight(temp.getRight());
                node.setLeft(temp.getLeft());
            }
        }

        return node;
    }

    private Movie minReleaseDate(Movie movie) {
        while (movie.getLeft() != null) {
            movie = movie.getLeft();
        }
        return movie;
    }

    public void traverse() {
        traverser(root);
        System.out.println();
    }

    private void traverser(Movie movie) {
        if (movie != null) {
            traverser(movie.getLeft());
            System.out.print(movie.getTitle() + " (" + movie.getReleaseDate() + ")," );
            traverser(movie.getRight());
        }
    }
}
