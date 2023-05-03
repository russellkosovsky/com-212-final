// TestMovieBinarySearchTree.java
package CORE;
public class TestMBST {
    public static void main(String[] args) {
        MovieBinarySearchTree movieBST = new MovieBinarySearchTree();

        Movie movieA = new Movie("Movie A", 2001, 00001, 85);
        Movie movieB = new Movie("Movie B", 1999, 00002, 70);
        Movie movieC = new Movie("Movie C", 2005, 00003, 95);
        Movie movieD = new Movie("Movie D", 2003, 00004, 80);

        movieBST.insert(movieA);
        movieBST.insert(movieB);
        movieBST.insert(movieC);
        movieBST.insert(movieD);

        System.out.println("Movie tree:");
        movieBST.printTree();

        int searchReleaseDate = 2001;
        Movie foundMovie = movieBST.search(searchReleaseDate);
        if (foundMovie != null) {
            System.out.println("Found movie with release date " + searchReleaseDate + ": " + foundMovie.getTitle());
        } else {
            System.out.println("No movie found with release date " + searchReleaseDate);
        }

        System.out.println("\nMovie tree after deleting Movie A:");
        movieBST.delete(movieA);
        movieBST.printTree();

        System.out.println("Inorder traversal:");
        movieBST.traverse();
    }
}


