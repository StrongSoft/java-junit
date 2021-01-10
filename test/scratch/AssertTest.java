package scratch;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AssertTest {

  static class InsufficientFundsException extends RuntimeException {

    InsufficientFundsException(String message) {
      super(message);
    }

    private static final long serialVersionUID = 1L;
  }

  static class Account {

    int balance;
    String name;

    Account(String name) {
      this.name = name;
    }

    void deposit(int dollars) {
      balance += dollars;
    }

    void withdraw(int dollars) {
      if (balance < dollars) {
        throw new InsufficientFundsException("balance only " + balance);
      }
      balance -= dollars;
    }

    public String getName() {
      return name;
    }

    public int getBalance() {
      return balance;
    }

    public boolean hasPositiveBalance() {
      return balance > 0;
    }
  }

  static class Customer {

    List<Account> accounts = new ArrayList<>();

    void add(Account account) {
      accounts.add(account);
    }

    Iterator<Account> getAccounts() {
      return accounts.iterator();
    }
  }

  private Account account;

  @Before
  public void createAccount() {
    account = new Account("an account name");
  }

  @Test
  public void hasPositiveBalance() {
    account.deposit(50);
    assertTrue(account.hasPositiveBalance());
  }

  @Test
  public void depositIncreasesBalance() {
    int initialBalance = account.getBalance();
    account.deposit(100);
    assertTrue(account.getBalance() > initialBalance);
    assertThat(account.getBalance(), equalTo(100));
  }

  @Test
  public void depositIncreasesBalance_hamcrestAssertTrue() {
    account.deposit(50);
    assertThat(account.getBalance() > 0, is(true));
  }

  @Ignore
  @Test
  public void matchesFailure() {
    assertThat(account.getName(), startsWith("xyz"));
  }

  @Ignore
  @Test
  public void comparesArraysFailing() {
    assertThat(new String[]{"a", "b", "c"}, equalTo(new String[]{"a", "b"}));
  }

  @Test
  public void comparesArraysPassing() {
    assertThat(new String[]{"a", "b"}, equalTo(new String[]{"a", "b"}));
  }

  @Ignore
  @Test
  public void comparesCollectionsFailing() {
    assertThat(Arrays.asList(new String[]{"a"}), equalTo(Arrays.asList(new String[]{"a", "ab"})));
  }

  @Test
  public void comparesCollectionsPassing() {
    assertThat(Arrays.asList(new String[]{"a"}), equalTo(Arrays.asList(new String[]{"a"})));
  }

  @Test
  public void variousMatcherTests() {
    Account account = new Account("my big fat acct");
    assertThat(account.getName(), is(equalTo("my big fat acct")));

    assertThat(account.getName(), not(equalTo("plunderings")));

    assertThat(account.getName(), is(not(nullValue())));
    assertThat(account.getName(), is(notNullValue()));
  }

  @Test
  public void testWithWorthlessAssertionComment() {
    account.deposit(50);
    assertThat("account balance is 100", account.getBalance(), equalTo(50));
  }

  @Test(expected = InsufficientFundsException.class)
  public void throwsWhenWithdrawingTooMuch() {
    account.withdraw(100);
  }

  @Test
  public void throwsWhenWithdrawingTooMuchTry() {
    try {
      account.withdraw(100);
      fail();
    } catch (InsufficientFundsException expected) {
      assertThat(expected.getMessage(), equalTo("balance only 0"));
    }
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void exceptionRule() {
    thrown.expect(InsufficientFundsException.class);
    thrown.expectMessage("balance only 0");

    account.withdraw(100);
  }

  @Test
  public void readsFromTestFile() throws IOException {
    String filename = "test.txt";
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
    writer.write("test data");
    writer.close();
  }
}
