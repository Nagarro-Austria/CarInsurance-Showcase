export class CarInsurancePage {
  public visit(): void {
    cy.visit('/');
  }

  carDetails(registrationDate: string, fuelType: string, co2Emissions: number, power: number) {
    cy.get('#firstRegistration').type(registrationDate);
    cy.get('mat-select[formControlName=fuelType]').click().get('mat-option').contains(fuelType).click();
    cy.get('#co2Emissions').type(co2Emissions.toString());
    cy.get('#power').type(power.toString());

    return {
      nextStep: function () {
        cy.get('#gotocontract').contains('Next').click();
      }
    }
  }

  contractDetails(startDate: string, coverage: string, bonusMalusLevel: string) {
    cy.get('#startDate').type(startDate);
    cy.get('#coverage').type(coverage);
    cy.get('mat-select[formControlName=bonusMaluslevel]').click().get('mat-option').contains(bonusMalusLevel).click();

    return {
      nextStep: function () {
        cy.get('#gotoperson').contains('Next').click();
      }
    }
  }

  personDetails(name: string, dayOfBirth: string, zipCode: string) {
    cy.get('#name').type(name);
    cy.get('#dayOfBirth').type(dayOfBirth);
    cy.get('#zipCode').type(zipCode);

    return {
      nextStep: function () {
        cy.get('#gotosummary').contains('Next').click();
      }
    }
  }

  getTax() {
    return cy.get('#tax');
  }

  getPremium() {
    return cy.get('#premium');
  }

}
