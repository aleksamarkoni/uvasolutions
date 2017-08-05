import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.lang.StringBuilder;

class Vote {
  public int c;
  public int current;
  public int votes[];
  public int validity[];
  public Vote(int c) {
    this.c = c;
    votes = new int[c];
    validity = new int[c];
  }
  
  public void addChoice(int choice) {
    votes[current] = choice;
    if (choice >= 1 && choice <= c)
      validity[choice-1] = choice;
    current++;
  }
  
  public boolean isValid() {
    for (int i = 0; i < c; i++) {
      if (validity[i] != (i+1))
        return false;
    }
    return true;
  }
  
  public int getIdOfCandidate(Set<Integer> stillValidCandidates) {
    for (int choice : votes) {
      if (stillValidCandidates.contains(choice))
        return choice;
    }
    return -1;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("valid: " + isValid() + " data: ");
    for (int choice : votes) {
      builder.append(choice + " ");
    }
    return builder.toString();
  }
}

class Candidate {
  public int id;
  public List<Vote> allVotesFor;
  public Candidate(int id) {
    this.id = id;
    allVotesFor = new ArrayList<>();
  }
  public void reset() {
    allVotesFor.clear();
  }
  
  public void addVote(Vote vote) {
    allVotesFor.add(vote);
  }
  
  @Override
  public String toString() {
    return "Id: " + id + " count: " + allVotesFor.size();
  }
}

class Candidates {
  public int c; 
  public int totalVotes;
  public Candidate[] candidates;
  public Set<Integer> stillValidCandidates;
  public Set<Integer> unvalidCandidates;
  Set<Integer> winners;
  
  public Candidates() {
    stillValidCandidates = new HashSet<>();
    unvalidCandidates = new HashSet<>();
    this.candidates = new Candidate[5];
    winners = new HashSet<>();
      for (int i = 0; i < 5; i++) {
        this.candidates[i] = new Candidate(i + 1);
      }
  }
  
  private void reset() {
    for (int i = 0; i < c; i++) {
      candidates[i].reset();
    }
    stillValidCandidates.clear();
    unvalidCandidates.clear();
    winners.clear();
    totalVotes = 0;
  }
  
  public void setNumOfCand(int c) {
    this.c = c;
    reset();
    for (int i = 0; i < c; i++)
      stillValidCandidates.add(i+1);
  }
  
  public void addVote(Vote vote) {
    int id = vote.getIdOfCandidate(stillValidCandidates);
    candidates[id - 1].addVote(vote);
    totalVotes++;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < c; i++) {
      builder.append(candidates[i]);
      builder.append(System.getProperty("line.separator"));
    }
    return builder.toString();
  }
  
  public boolean isThereAWinner() {
    calculatePossibleWinners();
    int neededToWin = totalVotes / 2;
    if (winners.size() == 1) {
      int id = winners.iterator().next();
      if (winners.size() == stillValidCandidates.size()) {
        //TODO check to see can we have such a situation. One candidate with one vote.
        System.out.println("   Candidate " + winners.iterator().next() + " is elected.");
        return true;
      } else if (candidates[id - 1].allVotesFor.size() > neededToWin) {
        System.out.println("   Candidate " + winners.iterator().next() + " is elected.");
        return true;
      } else {
        //System.out.println("There was an error here");
        return false;
      }
    } else if (winners.size() > 1){
      int id = winners.iterator().next();
      if (winners.size() == stillValidCandidates.size()) {
        //TODO check to see can we have such a situation. One candidate with one vote.
        System.out.print("   The following candidates are tied:");
        for (int winner : winners) {
          System.out.print(" " + winner);
        }
        System.out.println();
        return true;
      } else if (candidates[id - 1].allVotesFor.size() > neededToWin) {
        System.out.print("   The following candidates are tied:");
        for (int winner : winners) {
          System.out.print(" " + winner);
        }
        System.out.println();
        return true;
      } else {
        //System.out.println("There was an error here");
        return false;
      }
    }
    
    return false;
  }
  
  public void calculatePossibleWinners() {
    int currentMax = -1;
    for (int id : stillValidCandidates) {
      int numOfVotes = candidates[id - 1].allVotesFor.size();
      if (numOfVotes > currentMax) {
        winners.clear();
        winners.add(id);
        currentMax = numOfVotes;
      } else if (numOfVotes == currentMax) {
        winners.add(id);
      }
    }
  }
  
  private int calculateCandidateWithMinVotes() {
    int min = 200;
    int minid = -1;
    for (int id : stillValidCandidates) {
      int votes = candidates[id - 1].allVotesFor.size();
      if (votes < min) {
        min = votes;
        minid = id;
      }
    }
    return minid;
  }
  
  public void removeCandidateWithLowestNumOfVotes() {
    int minid = calculateCandidateWithMinVotes();
    unvalidCandidates.add(minid);
    stillValidCandidates.remove(minid);
    for (Vote vote : candidates[minid-1].allVotesFor) {
      int id = vote.getIdOfCandidate(stillValidCandidates);
      candidates[id - 1].addVote(vote);
    }
  }
}

public class Main {
  
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
    Candidates candidates = new Candidates();
    int testCount = 0;
    while (true) {
      int c = in.nextInt();
      int n = in.nextInt();
      if (c == 0 && n == 0) break;
      System.out.println("Election #" + (++testCount));
      candidates.setNumOfCand(c);
      int badVotes = 0;
      for (int i = 0; i < n; i++) {
        Vote vote = new Vote(c);
        for (int j = 0; j < c; j++) {
          vote.addChoice(in.nextInt());
        }
        //System.out.println(vote);
        if (vote.isValid()) {
          candidates.addVote(vote);
        } else {
          badVotes++;
        }
      }
      System.out.println("   " + badVotes + " bad ballot(s)");
      while (!candidates.isThereAWinner()) {
        candidates.removeCandidateWithLowestNumOfVotes();
      }
		}
	}
}
