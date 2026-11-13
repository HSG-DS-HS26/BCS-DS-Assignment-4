# Set up your group repository

Create one private GitHub repository for your pair. Both partners work from this
repository and record their names and GitHub handles in `TASKS.md`.

## 1. Create the group repository

1. Open this repository on GitHub. It is probably the page you are reading.
2. Press the green **`Use this template`** button, then **`Create a new repository`**.
3. Fill the form in:
    - Owner: one partner's account, not the organisation that owns this
      repository.
    - Repository name: `BCS-DS-Assignment-4`.
    - Visibility: **Private**. If you cannot select Private, contact the TA before continuing.
4. Press **`Create repository`**.

Your group repository is now at
`github.com/<owner-username>/BCS-DS-Assignment-4`.

> Use `Use this template` to create the repository. A fork remains tied to the
> original repository and cannot be graded.

## 2. Add your partner

1. In the group repository, go to **`Settings`** -> **`Collaborators`**.
2. Press **`Add people`**, choose your partner's GitHub account, and assign the
   **`Write`** role.

## 3. Add the TA as the examiner

Private repositories also hide your work from the TA. Grant us read access.

1. In the group repository, go to **`Settings`** -> **`Collaborators`**
   (under *Access* in the left sidebar).
2. Press **`Add people`**.
3. Type **`JeremyLemee`** and pick that account.
4. Set the role to **`Read`** and press **`Add ... to this repository`**.

`Read` lets the TA view your code, results, and commits without changing the
repository.

The TA must be able to open the repository before the deadline.


## 4. Work on your project

To work on the project, you need Java 21. We use Gradle to manage dependencies and the Gradle Wrapper to select the Gradle version. From the [`tasks/`](tasks) directory, use [`gradlew`](tasks/gradlew) on macOS/Linux and [`gradlew.bat`](tasks/gradlew.bat) on Windows. See [`TASKS.md`](TASKS.md) for detailed instructions.




## Submission

Hand in by **committing and pushing** to the group repository.

Commit and push regularly. Pushing does not run the assignment automatically.

Handing in has three stops, in this order, and [TASKS.md](TASKS.md) spells out
each one:

1. **After Task 3**, commit and push, then take the viva on[the viva site](https://wiser-sp4.interactions.ics.unisg.ch). The viva pushes its own record to your repository, so **pull** afterwards (**Sync Changes**).
2. **After Task 5**, commit and push again.
3. **Submit on Canvas.** Submit your [`Report.md`](Report.md) after writing down your names and GitHub project URL. 
If you include the video on Canvas, submit an archive containing [`Report.md`](Report.md), slides.pdf, and presentation.mp4.
Otherwise, commit and push slides.pdf and presentation.mp4 to your GitHub project, and only submit the [`Report.md`](Report.md) on Canvas .

## Git from the terminal

The Source Control panel does everything below. These are the same steps for
those who prefer a shell. In a Codespace, git and `gh` are installed and already
signed in as you, so there is nothing to configure. The commands are identical
in PowerShell.

### Commit

```bash
git status                      # what changed
git add -A                      # stage everything, or name paths instead
git commit -m "task 1: implement Lamport clocks"
```

When you pair, name both authors on the commit so the history shows the two of
you:

```bash
git commit -m "task 2: multithreaded crawler" -m "Co-authored-by: Ada Lovelace <ada@users.noreply.github.com>"
```

### Push

```bash
git push                        # the current branch, if it already tracks a remote
git push -u origin task-2       # the first push of a new branch
```

Your work reaches GitHub at this point and not before.

### Fetch, merge, pull

```bash
git fetch origin                # get the remote state, change nothing locally
git log --oneline HEAD..origin/main   # see what your partner pushed
git merge origin/main           # fold it into your branch
```

`git pull` is the two steps in one. Use it after your partner pushes, and after
the viva writes its record to your repository:

```bash
git pull
```

If a pull stops with conflicts, git marks the affected files. Edit each one,
remove the `<<<<<<<` markers, then:

```bash
git add <file>
git commit
```

`git merge --abort` returns you to the state before the merge.

### Branch and pull request

Pull requests are optional for this assignment. They are a good way to hand a
piece of work to your partner for review before it lands on `main`.

```bash
git switch -c task-2            # start a branch
git push -u origin task-2
gh pr create --fill --base main # opens the PR, prints its URL
```

Your partner reviews it on GitHub. Then merge it, either with the button there
or:

```bash
gh pr merge --squash --delete-branch
git switch main
git pull
```

### Take later fixes from the template

For integrating instructions about the live task and in rare cases we might be forced to publish corrections to the template repository after the assignment starts.
Add it once as a second remote:

```bash
git remote add template https://github.com/HSG-DS-HS26/BCS-DS-Assignment-4.git
git remote -v                   # origin is yours, template is ours
```

Then, whenever we announce a fix:

```bash
git fetch template
git log --oneline main..template/main
git merge template/main --allow-unrelated-histories
```

`Use this template` starts your repository with a fresh history, so that first
merge needs `--allow-unrelated-histories`. Expect to resolve conflicts in files
you have edited; keep your own work and take our change around it. Never push to
`template`, and you have no access to it anyway.

## Archive submission and direct viva

You may hand in a `.zip` archive instead of granting repository access. Tell the
TA before the deadline, submit the archive by the agreed route, and attend
the viva as a direct Teams call with the TA. An archive cannot be used for
the automated viva.

The direct Teams viva also applies if you do not want the automated AI viva to
read your GitHub repository. Tell the TA before the deadline, even if you
still submit through GitHub.

## If something goes wrong

- If `Use this template` is absent, check your sign-in and repository access, then
  ask the staff.
- If `Private` is absent, ask the staff before choosing a visibility setting.
- If you pressed `Fork` by mistake, delete the fork and start again from step 1.
  No assignment work exists there yet.
- If you already started working in the wrong place, stop and ask before
  moving the work.
