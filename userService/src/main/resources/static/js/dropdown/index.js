
      window.dropdown = function () {
        return {
          open: false,
          trigger: {
            ["@keydown.escape"]() {
              this.open = false;
            },
            ["@click"]() {
              this.open = true;
            },
          },
          dropdown: {
            ["@keydown.escape"]() {
              this.open = false;
            },
            ["x-show.transition"]() {
              return this.open;
            },
            ["@click.away"]() {
              this.open = false;
            },
          },
        };
      };