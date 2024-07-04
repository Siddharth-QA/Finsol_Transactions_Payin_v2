Feature:  Payin v2

  @APIx
  Scenario: Verify s2s INR
    When Verify post with subPayMode UPQR 5 times
    When Verify required fields
    When Verify OrderId already Exists
    When Verify Invalid Token
    When Verify post with subPayMode CLCT 5 times
    When Verify post with subPayMode ITNT 5 times

  @API
  Scenario: Verify non S2S INR
    When Verify non S2S required fields
    When Verify non s2s pay request 5 times
    When Verify OrderId already Exists non s2s
    When Verify Invalid Token non s2s

#  @API
#  Scenario: Verify s2s AUD
#    When Verify post with subPayMode dPayID 10 times
#    When Verify post with subPayMode sPayID 10 times
#
#  @API
#  Scenario: Verify non S2S AUD
#    When Verify non s2s pay request 10 times for nS2S



