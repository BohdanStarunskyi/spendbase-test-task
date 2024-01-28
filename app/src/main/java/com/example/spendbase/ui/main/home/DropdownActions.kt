package com.example.spendbase.ui.main.home

import com.example.spendbase.R

enum class DropdownActions(val textRes: Int, val endIconRes: Int) {
    ADD_FUNDS(R.string.add_funds, R.drawable.ic_left_down_arrow),
    WITHDRAW(R.string.withdraw, R.drawable.ic_arrow_top_right),
    CREATE_CARD(R.string.create_card, R.drawable.ic_card)
}