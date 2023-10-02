# Contributing to Animation-Transformer

Thank you for considering contributing to Animation-Transformer! Your contributions help make this project better.

Before you start contributing, please take a moment to read and understand the contribution guidelines below.

## Commit Message Conventions

We follow the Angular Commit Message Conventions for our Git commit messages. This convention helps us automatically generate changelogs and version releases using semantic-release.

Each commit message should have the following format:

`<type>(<scope>): <subject>`

- `<type>`: Describes the purpose of the commit (e.g., feat, fix, chore, docs, style, refactor, test).
- `<scope>` (optional): Describes the scope of the change (e.g., component name, module).
- `<subject>`: A short, descriptive message of the change.

For example:
- `feat(user-auth): add new login functionality`
- `fix(order-processing): resolve issue with order validation`

Please make sure to use this commit message format when making changes and creating Merge Requests.

## Creating a Merge Request

When creating a Merge Request (MR) on GitLab, please follow these guidelines:

1. Title: Use a clear and concise title that summarizes the purpose of the MR.
2. Description: Provide detailed information about the changes you have made. Include the problem you're addressing, the solution you've implemented, and any relevant context.
3. Reference Issues: If your MR addresses an existing issue, please reference it in your description using the syntax `Closes #<issue-number>`.
4. Assignee: Assign the MR to the relevant team member or maintainers.
5. Reviewers: Add team members or maintainers as reviewers if necessary.
6. Labels: Apply appropriate labels to categorize the MR (e.g., bug, feature, enhancement).
7. Milestone: If applicable, assign the MR to a milestone.

## CI/CD Pipeline and Release

Our CI/CD pipeline and version releases are automated using semantic-release. To ensure that your changes trigger the pipeline and release process correctly, please follow the commit message conventions mentioned above.

Once your MR is merged, semantic-release will automatically determine the new version number based on the commit messages and generate changelogs accordingly.

Thank you for your contribution to Animation-Transformer! We appreciate your efforts in helping us improve this project.

Happy coding!