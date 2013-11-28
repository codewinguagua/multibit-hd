package org.multibit.hd.ui.controllers;

import com.google.common.eventbus.Subscribe;
import com.xeiam.xchange.currency.MoneyUtils;
import org.joda.money.BigMoney;
import org.multibit.hd.core.events.ExchangeRateChangeEvent;
import org.multibit.hd.ui.events.ViewEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * <p>Controller for the Main view </p>
 * <ul>
 * <li>Handles interaction between the model and the view</li>
 * </ul>
 * <p>To allow complete separation between Model, View and Controller all interactions are handled using application events</p>
 */
public class MainController {

  private static final Logger log = LoggerFactory.getLogger(MainController.class);

  public MainController() {
  }

  /**
   * <p>Called when the balance changes</p>
   *
   * @param event The exchange rate change event
   */
  @Subscribe
  public void onBalanceChanged(ExchangeRateChangeEvent event) {

    // Build the exchange string
    // TODO Link to a real balance
    BigMoney btcBalance = MoneyUtils.parseMoney("BTC",new BigDecimal("20999999.12345678"));
    BigMoney localBalance = btcBalance.multipliedBy(event.getRate());

    // Post the event
    ViewEvents.fireBalanceChangeEvent(btcBalance, localBalance, event.getExchangeName());

  }

}
