package com.example.spendbase.domain

import com.example.spendbase.R
import com.example.spendbase.domain.entites.CardEntity
import com.example.spendbase.domain.entites.transactions.TransactionEntity
import com.example.spendbase.domain.entites.transactions.TransactionStatusesEnum

fun getFakeTransaction() = TransactionEntity(
    startIconResource = R.drawable.ic_left_down_arrow,
    title = "Transfer In",
    cardNumber = "•• 7544",
    status = TransactionStatusesEnum.DONE,
    endIconResource = R.drawable.ic_transaction,
    amount = 1000
)

fun getHomeTransactions() = listOf(
    TransactionEntity(
        startIconResource = R.drawable.ic_left_down_arrow,
        title = "Transfer In",
        cardNumber = "•• 7544",
        status = TransactionStatusesEnum.DONE,
        endIconResource = R.drawable.ic_transaction,
        amount = 1000
    ), TransactionEntity(
        startIconResource = R.drawable.ic_card,
        title = "Google",
        cardNumber = "",
        status = TransactionStatusesEnum.DECLINED,
        amount = -1299
    )
)

fun getOct12Transactions() = listOf(
    TransactionEntity(
        startIconResource = R.drawable.ic_left_down_arrow,
        title = "Ryanair",
        cardNumber = "•• 7544",
        status = TransactionStatusesEnum.DONE,
        endIconResource = R.drawable.ic_transaction,
        amount = -499
    ),
    TransactionEntity(
        startIconResource = R.drawable.ic_card,
        title = "Uber",
        cardNumber = "•• 8455",
        status = TransactionStatusesEnum.DONE,
        amount = -140,
        endIconResource = R.drawable.ic_no_invoice
    ),
    TransactionEntity(
        startIconResource = R.drawable.ic_card,
        title = "Hotel",
        cardNumber = "•• 8503",
        status = TransactionStatusesEnum.DONE,
        amount = -63,
        endIconResource = R.drawable.ic_transaction
    )
)

fun getOct11Transactions() = listOf(
    TransactionEntity(
        startIconResource = R.drawable.ic_left_down_arrow,
        title = "Transfer in",
        cardNumber = "•• 7544",
        status = TransactionStatusesEnum.DONE,
        endIconResource = R.drawable.ic_transaction,
        amount = 7500
    ),
    TransactionEntity(
        startIconResource = R.drawable.ic_card,
        title = "Turkish Airlines",
        cardNumber = "",
        status = TransactionStatusesEnum.DECLINED,
        amount = -1299
    ),
    TransactionEntity(
        startIconResource = R.drawable.ic_card,
        title = "Restaurant",
        cardNumber = "•• 4163",
        status = TransactionStatusesEnum.DONE,
        amount = -499,
        endIconResource = R.drawable.ic_transaction
    ),
    TransactionEntity(
        startIconResource = R.drawable.ic_card,
        title = "Uber",
        cardNumber = "•• 3872",
        status = TransactionStatusesEnum.DONE,
        amount = -20,
        endIconResource = R.drawable.ic_no_invoice
    )
)

fun getFakeCards() = listOf(
    CardEntity(
        number = "4141",
        name = "Virtual card"
    ),
    CardEntity(
        number = "4141",
        name = "Slack"
    ),
    CardEntity(
        number = "4141",
        name = "Google"
    )
)