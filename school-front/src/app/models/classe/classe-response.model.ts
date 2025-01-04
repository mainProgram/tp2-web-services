export interface IClasseResponse {
  id: number;
  libelle: string;
  professeursIds: [number] | null;
  matieresIds: [number] | null;
  etudiantsIds: [number] | null;
}
