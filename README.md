# mini-bank
create a simple bank app that perfom basic transaction in banking system

TODO 
Functionality: 
- Create new user
- Create multiple account 
- Inquiry Account 
- Inquiry account details
- Inquiry transaction
- transfer overbooking

  
# RAW TABLE
default value 
- SYSTEM_REG_DTM
- SYSTEM_UPD_DTM
- USER_REG_NM
- USER_UPD_NM


USER
- id
- first_name
- last_name
- email
- phone_no
- address
- register_date
- status -> code 
- job
- country
- isAdmin

USER_ACTIVITY
- id
- user_id
- login_password
- login_error_cnt
- password_error_cnt
- isAdmin
- transaction_password
- transaction_error_cnt
- trsc_dt
- debit_pin_no
- last_login_dt
- last_login_tm


ACCOUNT
- id
- user_id
- account_name
- card_no
- account_no
- account_sts
- card_sts
- account_type
- ballance
- account_med_no


TRANSACTION
- id
- user_id
- transcation_date
- transcation_tm
- status
- withdrawal_account
- withdrawal_name
- withdrawal_bank_cd
- receive_account_no
- receive_bank_cd
- remark
- transcation_amount
- transcation_fee
- reff_no


TRANSACTION_HISTORY
- id
- trnsc_dt
- user_id
- account_no
- remark
- credit/debit
- transcation_amout
- balance

