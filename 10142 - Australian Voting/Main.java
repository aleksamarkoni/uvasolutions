import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
Worst problem ever, so much code for nothing. 
I was getting too much of the time limit, I did couple of optimization's, but this
java input stuff sucks.
**/

class Vote {
  public static int numOfVotes;
  public int votePointer = 0;
  public int current;
  public int votes[];
  public int validity[];
  public Vote() {
    this.votePointer = 0;
    votes = new int[20];
    validity = new int[20];
  }
  
  public void addChoice(int choice) {
    votes[current] = choice;
    if (choice >= 1 && choice <= numOfVotes)
      validity[choice-1] = choice;
    current++;
  }
  
  public void reset() {
    votePointer = 0;
    current = 0;
    numOfVotes = 0;
  }
  
  public void setNumOfVotes(int numOfVotes) {
    this.numOfVotes = numOfVotes;
  }
  
  public boolean isValid() {
    for (int i = 0; i < numOfVotes; i++) {
      if (validity[i] != (i+1))
        return false;
    }
    return true;
  }
  
  public int getIdOfCandidate(Set<Integer> stillValidCandidates) {
    for (; votePointer < votes.length; votePointer++) {
      if (stillValidCandidates.contains(votes[votePointer]))
        return votes[votePointer];
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
  public String name;
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
  
  private final int NUM_OF_CANDIDATES = 20;
  public int c; 
  public int totalVotes;
  public Candidate[] candidates;
  public Vote[] votes;
  public int pVote;
  public Set<Integer> stillValidCandidates;
  public Set<Integer> unvalidCandidates;
  Set<Integer> winners;
  
  public Candidates() {
    stillValidCandidates = new HashSet<>();
    unvalidCandidates = new HashSet<>();
    this.candidates = new Candidate[NUM_OF_CANDIDATES];
    this.votes = new Vote[1000];
    this.pVote = 0;
    winners = new HashSet<>();
    for (int i = 0; i < NUM_OF_CANDIDATES; i++) {
      this.candidates[i] = new Candidate(i + 1);
    }
    for (int i = 0; i < 1000; i++) {
      this.votes[i] = new Vote();
    }
  }
  
  private void reset() {
    for (int i = 0; i < c; i++) {
      candidates[i].reset();
    }
    for (int i = 0; i < 1000; i++) {
      this.votes[i].reset();
    }
    this.pVote = 0;
    stillValidCandidates.clear();
    unvalidCandidates.clear();
    winners.clear();
    totalVotes = 0;
  }
  
  public void setNumOfCand(int c) {
    this.c = c;
    reset();
    Vote.numOfVotes = c;
    for (int i = 0; i < c; i++) {
      stillValidCandidates.add(i+1);
    }
  }
  
  public void addVote(String line) {
    
    char c;
    int i = 0;
    int num = 0;
    while (i < line.length()) {
      c = line.charAt(i++);
      switch (c) {
        case ' ':
          if (num != 0)
            this.votes[pVote].addChoice(num);
          num = 0;
          break;
        default:
          num = num * 10 + (c - '0');
          break;
      }   
    }
    if (num != 0)
      this.votes[pVote].addChoice(num);
    
    if (this.votes[pVote].isValid()) {
      int id = this.votes[pVote].getIdOfCandidate(stillValidCandidates);
      candidates[id - 1].addVote(this.votes[pVote]);
      totalVotes++;
      pVote++;
    }
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
        System.out.println(candidates[winners.iterator().next() - 1].name);
        return true;
      } else if (candidates[id - 1].allVotesFor.size() > neededToWin) {
        System.out.println(candidates[winners.iterator().next() - 1].name);
        return true;
      } else {
        //System.out.println("There was an error here");
        return false;
      }
    } else if (winners.size() > 1){
      int id = winners.iterator().next();
      if (winners.size() == stillValidCandidates.size()) {
        //TODO check to see can we have such a situation. One candidate with one vote.
        for (int i = 0; i < c; i++) {
          if (winners.contains(candidates[i].id))
            System.out.println(candidates[i].name);
        }
        return true;
      } else if (candidates[id - 1].allVotesFor.size() > neededToWin) {
        for (int i = 0; i < c; i++) {
          if (winners.contains(candidates[i].id))
            System.out.println(candidates[i].name);
        }
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
  
  private List<Integer> calculateCandidateWithMinVotes() {
    List<Integer> mins = new ArrayList<>();
    int min = 200;
    for (int id : stillValidCandidates) {
      int votes = candidates[id - 1].allVotesFor.size();
      if (votes < min) {
        mins.clear();
        min = votes;
        mins.add(id);
      } else if (votes == min) {
        mins.add(id);
      }
    }
    return mins;
  }
  
  public void removeCandidateWithLowestNumOfVotes() {
    List<Integer> mins = calculateCandidateWithMinVotes();
    for (int id : mins) {
      unvalidCandidates.add(id);
      stillValidCandidates.remove(id);
    }
    for (int id : mins) {
      for (Vote vote : candidates[id-1].allVotesFor) {
        int idc = vote.getIdOfCandidate(stillValidCandidates);
        candidates[idc - 1].addVote(vote);
      }
    }
  }
}

public class Main {
  
	public static void main1(String[] args) throws Exception {
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
    Candidates candidates = new Candidates();
    int testCount = Integer.parseInt(bi.readLine());
    bi.readLine();
    long totalInputTime = 0;
    long totalAddVoteTime = 0;
    long startTime = System.currentTimeMillis();
    while (testCount-- != 0) {
      int n = Integer.parseInt(bi.readLine());
      //System.out.println("n: " + n);
      candidates.setNumOfCand(n);
      for (int i = 0; i < n; i++) {
        candidates.candidates[i].name = bi.readLine();
        //System.out.println(candidates.candidates[i].name);
      }
      long inputStartTime = System.currentTimeMillis();
      String line;
      while((line = bi.readLine()) != null) {
        if (line.isEmpty()) {
          //System.out.println("break");
          break;
        }
        //System.out.println(line);
        long addVoteStart = System.currentTimeMillis();
        candidates.addVote(line);
        long addVoteEnd = System.currentTimeMillis();
        long addVoteTime = addVoteEnd - addVoteStart;
        totalAddVoteTime += addVoteTime;
      }
      long inputEndTime = System.currentTimeMillis();
      long inputTotalTime = inputEndTime - inputStartTime;
      totalInputTime += inputTotalTime;
      //System.out.println(inputTotalTime);
      long processStartTime = System.currentTimeMillis();
      while (!candidates.isThereAWinner()) {
        candidates.removeCandidateWithLowestNumOfVotes();
      }
      long processEndTime = System.currentTimeMillis();
      long processTotalTime = processEndTime - processStartTime;
      //System.out.println(processTotalTime);
      if (testCount != 0) {
        System.out.println();
      }
		}
    long endTime   = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.println(totalInputTime);
    System.out.println(totalAddVoteTime);
    System.out.println(totalTime);
	}
  
  public static void main(String[] args) throws Exception {
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
    int testCount = Integer.parseInt(bi.readLine());
    bi.readLine();
    String names[] = new String[20];
    boolean [] eliminated = new boolean[20];
    int [][] ratings = new int[1000][];
    for (int i = 0; i < 1000; i++) {
      ratings[i] = new int[20];
    }
    int [] posInRatings = new int[1000];
    int [] count = new int[20];
    int pos;
    char c;
    int k;
    int num;
    int numRatings;
    long totalInputTime = 0;
    long totalAddVoteTime = 0;
    int winner;
    //long startTime = System.currentTimeMillis();
    while (testCount-- != 0) {
      int n = Integer.parseInt(bi.readLine());
      for (int i = 0; i < n; i++) {
        names[i] = bi.readLine();
      }
      //long inputStartTime = System.currentTimeMillis();
      
      numRatings = 0;
      
      String line;
      while((line = bi.readLine()) != null) {
        if (line.isEmpty()) {
          //System.out.println("break");
          break;
        }
        
        //System.out.println(line);
        //long addVoteStart = System.currentTimeMillis();
        pos = 0;
        k = 0;
        num = 0;
        while (k < line.length()) {
          c = line.charAt(k++);
          switch (c) {
            case ' ':
              if (num != 0)
                ratings[numRatings][pos++] = num - 1;
              num = 0;
              break;
            default:
              num = num * 10 + (c - '0');
              break;
          }   
        }
        if (num != 0)
          ratings[numRatings][pos++] = num - 1;
        numRatings++;
        //long addVoteEnd = System.currentTimeMillis();
        //long addVoteTime = addVoteEnd - addVoteStart;
        //totalAddVoteTime += addVoteTime;
      }
      //long inputEndTime = System.currentTimeMillis();
      //long inputTotalTime = inputEndTime - inputStartTime;
      //totalInputTime += inputTotalTime;
      //System.out.println(inputTotalTime);
      //long processStartTime = System.currentTimeMillis();
    
      // System.out.println("Size " + numRatings);
      // for (int i = 0; i < numRatings; i++) {
      //   for (int j = 0; j < n; j++) {
      //     System.out.print(" " + ratings[i][j]);
      //   }
      //   System.out.println();
      // }
      
      for (int i = 0; i < n; i++) {
        eliminated[i] = false;
      }
      
      for (int i = 0; i < numRatings; i++) {
        posInRatings[i] = 0;
      }
      winner = -1;
      for (int i = 0; i < n; i++) {
        count[i] = 0;
      }
      
      
      for (int i = 0; i < numRatings; i++)
        ++count[ratings[i][0]];
        
      while (winner == -1) {
        for (int i = 0; i < numRatings; i++) {
          boolean changed = false;
          while (posInRatings[i] < n && eliminated[ratings[i][posInRatings[i]]]) {
            ++posInRatings[i];
            changed = true;
          }
          if (changed)
            ++count[ratings[i][posInRatings[i]]];
        }
        
        int highest = 0;
        int lowest = 1000;
        for (int i = 0; i < n; i++) {
          if (eliminated[i])
            continue;
          if (count[i] > highest)
            highest = count[i];
          if (count[i] < lowest)
            lowest = count[i];
        }
        
        if (highest == lowest || highest * 2 > numRatings)
          winner = highest;
        else {
          for (int i = 0; i < n; i++) {
            if (count[i] == lowest) {
              eliminated[i] = true;
            }
          }
        }
      }
      
      //long processEndTime = System.currentTimeMillis();
      //long processTotalTime = processEndTime - processStartTime;
      
      for (int i = 0; i < n; i++) {
        if (count[i] == winner && !eliminated[i]) {
          System.out.println(names[i]);
        }
      }
      
      if (testCount != 0) {
        System.out.println();
      }
		}
    //long endTime   = System.currentTimeMillis();
    //long totalTime = endTime - startTime;
    //System.out.println(totalInputTime);
    //System.out.println(totalAddVoteTime);
    //System.out.println(totalTime);
	}
}
