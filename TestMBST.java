import CORE.*;
// TestMovieBinarySearchTree.java
public class TestMBST {
    public static void main(String[] args) {
        MovieBinarySearchTree movieBST = new MovieBinarySearchTree();

        Movie movie1 = new Movie("Movie 1", 2001, 1, 85, true);
        Movie movie2 = new Movie("Movie 2", 2002, 2, 70, true);
        Movie movie3 = new Movie("Movie 3", 2003, 3, 95, true);
        Movie movie4 = new Movie("Movie 5", 2004, 4, 80, true);
        Movie movie5 = new Movie("Movie 6", 2005, 5, 85, true);
        Movie movie6 = new Movie("Movie 7", 2006, 6, 70, true);
        Movie movie7 = new Movie("Movie 8", 2007, 7, 95, true);
        Movie movie8 = new Movie("Movie 9", 2008, 8, 80, true);

        movieBST.insert(movie1);
        movieBST.insert(movie6);
        movieBST.insert(movie2);
        movieBST.insert(movie8);
        movieBST.insert(movie3);
        movieBST.insert(movie7);
        movieBST.insert(movie4);
        movieBST.insert(movie5);


        System.out.println("printTree:");
        movieBST.printTree();

        System.out.println("traverse:");
        movieBST.traverseDate();

        System.out.println("\nsearchReleaseDate... for 2001:");
        int searchReleaseDate = 2001;
        Movie foundMovie = movieBST.searchDate(searchReleaseDate);

        if (foundMovie != null) {
            System.out.println("Found movie with release date " + searchReleaseDate + ": " + foundMovie.getTitle());
        } else {
            System.out.println("No movie found with release date " + searchReleaseDate);
        }

        

        System.out.println("\nMovie tree after deleting Movie 5:");
        movieBST.delete(movie5);
        movieBST.printTree();

        System.out.println("Inorder traversal:");
        movieBST.traverse();
    }
}


