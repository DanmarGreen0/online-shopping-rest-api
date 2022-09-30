## Risk and Impact Analysis
Users need a way of securing their accounts from other users. Without a security functionality, different users may potentially steal other user accounts or information. Therefore, security is a vital feature of any computer application, especially web applications that store important or confidential data. Also, know would ever want to use an application that exposes their information to the entire world.

## Overview
I have implemented a security feature that allows users to log in to their accounts. I have also implemented web service endpoint security that enforces user access. However, once a user logs into their account using a web browser, they can't log out of their account unless they end the browser session. In a use case where multiple users interact with a single device to sign in to their accounts, this security flaw would leave any user at risk of another user using their account. 

## New feature/API (non-breaking change which adds functionality)
- security
  - user login their account
  - admin user can view all user accounts and their information
  - basic user can only view their account information

## Configuration change
## Other, explain here:
Features
Bug Fixes
Reviewer Notes
## Checklist:
I have added unit tests that cover the new code
e,g, I have successfully executed make build and make test
My code follows the code style of this project.
I have updated the documentation/wiki accordingly.
I have communicated my change to ...
## Release Agreement
DO NOT DELETE THIS SECTION

I understand the following implications of merging this Pull Request into the main branch

this Pull Request is approved by at least one CODEOWNER who is not me, and other appropriate approvers 
a deployment pipeline will be triggered with the potential of reaching Production
in the event of a defective deployment, the rollback plan must be followed, and also a fix or revert of this merge commit performed.
Deployment progress (on commit) is available here(Edited)
